function checkRePassword() {
    var rePassword = $("#rePassword").val();
    if (rePassword == null || rePassword == '') {
        $("#rePassword_msg").html("请再次输入密码!");
        return false;
    }
    if (rePassword != $("#password").val()) {
        $("#rePassword_msg").html("两次密码不一致!");
        return false;
    } else {
        $("#rePassword_msg").css('color', 'green');
        $("#rePassword_msg").html("密码可用!");
        return true;
    }
}

//先校验手机是否符合规范
function ajaxCheckStuMobile() {
    var stuMobile = $("#stuMobile").val();
    if (stuMobile == null || stuMobile == '') {
        $("#stuMobile_msg").html("手机号不能为空!");
        return false;
    }
    var test_stuMobile = /^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/;
    if (stuMobile.length != 11) {
        show_validate_msg("#stuMobile", "error", "号码需11位!");
        return false;
    }
    if (!test_stuMobile.test(stuMobile)) {
        show_validate_msg("#stuMobile", "error", "号码不符合规范");
        $("#stuMobileBtn").hide();
        return false;
    } else {
        show_validate_msg("#stuMobile", "success", "号码可用!");
        $("#stuMobileBtn").show();
        return true;
    }
}

$(function () {
    /*仿刷新：检测是否存在cookie*/
    if ($.cookie("captcha")) {
        var count = $.cookie("captcha");
        var btn = $('#stuMobileBtn');
        btn.val(count + '秒后再获取').attr('disabled', true).css('cursor', 'not-allowed');
        var resend = setInterval(function () {
            count--;
            if (count > 0) {
                btn.val(count + '秒后再获取').attr('disabled', true).css('cursor', 'not-allowed');
                $.cookie("captcha", count, {path: '/', expires: (1 / 86400) * count});
            } else {
                clearInterval(resend);
                btn.val("获取验证码").removeClass('disabled').removeAttr('disabled style');
            }
        }, 1000);
    }
    /*点击改变按钮状态，已经简略掉ajax发送短信验证的代码*/
    $('#stuMobileBtn').click(function () {
        /* if (!ajaxCheckStuMobile()) {
             return false;
         }*/
        if($('#stuMobile').val()==null||$('#stuMobile').val()==''){
            $("#stuMobile_msg").css('color', 'red');
            $("#stuMobile_msg").html("请正确填写手机号!");
            return false;
        }
        var btn = $(this);
        var count = 60;
        var resend = setInterval(function () {
            count--;
            if (count > 0) {
                btn.val(count + "秒后再获取");
                $.cookie("captcha", count, {path: '/', expires: (1 / 86400) * count});
            } else {
                clearInterval(resend);
                btn.val("获取验证码").removeAttr('disabled style');
            }
        }, 1000);
        btn.attr('disabled', true).css('cursor', 'not-allowed');
        //发送验证码
        sendMobileCode();
    });
});


//发送手机验证码
function sendMobileCode() {
    //异步校验用户名是否已经注册
    $.ajax({
        url: "/yxw/ajaxCheckMobileCode.json",
        data: {"stuMobile": $("#stuMobile").val()},
        type: "POST",
        dataType: "json",
        success: function (msg) {
            if (msg == 1) {
                $("#stuMobile_msg").css('color', 'green');
                $("#stuMobile_msg").html("验证码以发送!");
            } else {
                $("#stuMobile_msg").html(msg)
            }
        },
        error: function () {
        }
    })
}


//先校验用户名 是否符合 规范
function ajaxCheckStuUsername() {
    //前端正则表达式验证
    if (!validate_add_form_username()) {
        return false;
    }
    //异步校验用户名是否已经注册
    $.ajax({
        url: "/yxw/ajaxCheckStuUsername.json",
        data: {"stuUsername": $("#stuUsername").val()},
        type: "post",
        dataType: "json",
        success: function (msg) {
            if (msg == 1) {
                $("#stuUsername_msg").html("用户名已存在!");
                $("#stuUsername_msg").css('color', 'red');
            } else {
                //用户名可用
                $("#stuUsername_msg").html("用户名可使用")
                $("#stuUsername_msg").css('color', 'green');
            }

        },
        error: function () {
        }
    })
}

function checkStuPassword() {

    //先校验密码是否符合规范
    //前端正则表达式验证
    if (!validate_add_form_password()) {
        return false;
    }
}


function ajaxGetSchoolByProvinceChange() {
    //获取省份对应的编号值
    var provinceNum = $("#provinceNum").val();
    //根据身份编号值ajax加载对应的省份高校
    //alert(provinceNum);
    $.ajax({
        type: "GET",
        url: "/yxw/register_getSchoolByProvinceNum.json",
        data: {provinceNum: provinceNum},
        dataType: "json",
        success: function (data) {
            // alert(data);
            var html = '<option value="" selected>--请选择--</option>';
            for (var i = 0; i < data.length; i++) {
                html += '<option value="';
                html += data[i].schoolNum;
                html += '">';
                html += data[i].schoolName;
                html += '</option>';
            }
            $("#schoolNum").html(html);
        }
    });
}

$(function () {
    $("#user_insert_btn_reg").click(function () {

        if ($("#checkAgreed").attr('checked') != 'checked') {
            alert("请先同意注册条款!");
            return false;
        }
        var username = $("#stuUsername").val();
        var password = $("#password").val();
        var rePassword = $("#rePassword").val();
        var kaptcha = $("#kaptcha").val();
        var stuMobile = $("#stuMobile").val();
        var stuMobileCode = $("#stuMobileCode").val();
        if (username.length == 0) {
            $("#stuUsername_msg").html("用户名不能为空!");
            return false;
        }
        if (password.length == 0) {
            $("#password_msg").html("密码不能为空!");
            return false;
        }
        if (stuMobile.length == 0) {
            $("#stuMobile_msg").html("号码不能为空!");
            return false;
        }
        if (rePassword.length == 0) {
            $("#stuMobile_msg").html("请确认密码!");
            return false;
        }
        if (kaptcha.length == 0) {
            $("#kaptcha_msg").html("验证码不能为空!");
            return false;
        }
        if (stuMobileCode.length == 0) {
            $("#stuMobileCode_msg").html("手机验证码不能为空!");
            return false;
        }
        return true;

    });
});

