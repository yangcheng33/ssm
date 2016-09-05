package com.github.vita.ssm.common.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


/**
 * 为独立于数据库而设计的Sequence生成器
 *
 * @author HuQingmiao
 */
public class SeqGenerator {

    private final static Logger log = LoggerFactory.getLogger(SeqGenerator.class);

    private final static short DEFAULT_STEP = 1;        // 默认增量

    private final static int DEFAULT_MIN_VALUE = 101;  // 默认最小值

    private final static long DEFAULT_MAX_VALUE = 999999999999999L;//默认最大值

    private final static String QUERY_SQL = "SELECT value,step,max_value FROM seq_library WHERE name=? FOR UPDATE";

    private final static String UPDATE_SQL = "UPDATE seq_library SET value=? WHERE name =? ";

    private final static String INSERT_SQL = "INSERT INTO seq_library(name,value,step,min_value,max_value) VALUES(?,?,?,?,?)";


    private static SqlSessionFactory sessionFactory = null;
    static {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            sessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
            //sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(mybatis.xml"));
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private SeqGenerator() {
    }

    /**
     * 根据sequence名称取下一个值
     *
     * @param seqName
     * @return
     * @throws Exception
     */
    public static long getNextVal(String seqName) throws SQLException {
        long nextVal = 0;

        SqlSession sqlSession = sessionFactory.openSession();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = sqlSession.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(QUERY_SQL);
            stmt.setString(1, seqName);
            rs = stmt.executeQuery();

            //如果不存在该sequence，则关闭连接以释放悲观锁，然后创建sequence
            if (!rs.next()) {
                releaseResource(conn, stmt, rs);
                return createSequence(seqName);
            }

            int k = 1;
            int currVal = rs.getInt(k++);
            short step = rs.getShort(k++);
            long maxValue = rs.getLong(k++);
            if (currVal <= maxValue - step) {
                nextVal = currVal + step;
            } else {
                throw new SQLException("Sequence " + seqName + "已经达到最大值!");
            }
            rs.close();
            stmt.close();

            //更新该sequence的当前值
            stmt = conn.prepareStatement(UPDATE_SQL);

            k = 1;
            stmt.setLong(k++, nextVal);
            stmt.setString(k++, seqName);
            stmt.executeUpdate();
            conn.commit();

        } finally {
            releaseResource(conn, stmt, rs);
            sqlSession.close();
        }
        return nextVal;
    }

    //创建一条Sequence记录, 并返回第一个值
    private static long createSequence(String seqName) throws SQLException {

        SqlSession sqlSession = sessionFactory.openSession();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = sqlSession.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement(INSERT_SQL);
            int k = 1;
            stmt.setString(k++, seqName);
            stmt.setLong(k++, new Long(DEFAULT_MIN_VALUE));
            stmt.setShort(k++, new Short(DEFAULT_STEP));
            stmt.setInt(k++, new Integer(DEFAULT_MIN_VALUE));
            stmt.setLong(k++, new Long(DEFAULT_MAX_VALUE));
            stmt.executeUpdate();
            conn.commit();

        } finally {
            releaseResource(conn, stmt, rs);
            sqlSession.close();
        }

        return DEFAULT_MIN_VALUE;
    }

    private static void releaseResource(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(">>>>>>>>>>");
            long b = new Date().getTime();
            for (int i = 0; i < 50; i++) {
                System.out.println(SeqGenerator.getNextVal("a"));
            }
            long c = new Date().getTime();

            System.out.println(">>" + (c - b));// 1980
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

