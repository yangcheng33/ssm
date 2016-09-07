
// 将form序列化为json字符串
$.fn.serializeObject = function () {
    var obj = {};
    var count = 0;
    $.each(this.serializeArray(), function (i, o) {
        var n = o.name, v = o.value;
        count++;
        obj[n] = obj[n] === undefined ? v
            : $.isArray(obj[n]) ? obj[n].concat(v)
            : [obj[n], v];
    });
    obj.nameCounts = count + "";//表单name个数
    return JSON.stringify(obj);
};


var submitFlag = false;//是否正在提交

//检查用户是否重复提交
function isNotDupSubmit() {

    //如果正在提交,则返回false, 表示当前动作是重复提交
    if (submitFlag == true) {
        alert("请不要重复操作！");
        return false;
    }

    //否则，设置标志表示正在提交; 并返回true, 表示当前动作是正常提交.
    submitFlag = true;
    return true;
}


//弹出新窗口
function popWindow(myurl, winWidth, winHight) {
    var childWin;

    //弹出窗口在离屏幕上方1/4; 离屏幕左边1/3处
    var topW = (window.screen.height) / 5;
    var leftW = (window.screen.width) / 3 - 50;

    childWin = window.open(myurl, "UserRefWin1", "top=" + topW + "; left=" + leftW + "; height=" + winHight + ",width=" + winWidth + ",status=NO,toolbar=NO,menubar=NO,scrollbars=Yes,location=NO,resizable=NO,alwaysRaised=YES,titlebar=no");
    childWin.parentWin = this;
}


//取字符串的字节长度
function lengthB(str) {
    var len = str.length;
    for (var i = 0; i < str.length; i++) {
        var code = str.charCodeAt(i);

        //对'×'('×'.charCodeAt(0)=215)符号作特殊处理,其在oracle中占两个英文字符的长度
        if ((code > 0 && code < 255 && code != '215')
            || (code > 65381 && code < 65440)) {
        } else {
            len += 2;
        }
    }
    return len;
}


//判断该字符串是否全为数字
function isDigit(value) {
    if (/\D/g.test(value)) {
        return false;
    }
    return true;
}

//将回车符转换为TAB，使得当用户按下回车键时，焦点移至下一个对象
function enterToNext() {
    if (event.keyCode == 13) {
        event.keyCode = 9;
    }
    return;
}









