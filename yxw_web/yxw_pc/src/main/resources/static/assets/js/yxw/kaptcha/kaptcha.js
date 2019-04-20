var count = 0;
var _time = 5;

//图形验证码
function getImgCode() {

    if (count < 5) {
        _time = 5;
        count++;
        $('#kaptchaImage').attr('src', '/kaptcha.jpg?time=' + Math.floor(Math.random() * 100));
    } else {
        alert("短时间连续点击!请5秒后再试!")
        $('#kaptchaImage2').html(_time + "秒后再试");
        countDown();
    }
}

//使用匿名函数方法
function countDown() {
    if (_time > 0) {
        $('#kaptchaImage2').html(_time-- + "秒后再试");
        var interval = setInterval("countDown()", 1000);
        if (_time == 0) {
            clearInterval(interval);
        }
    } else {
        count = 0;
        $('#kaptchaImage2').html('点击换张');
        return false;
    }
    //获取到id为time标签中的内容，现进行判断

}


$(function () {

    $('#kaptchaImage').click(function () {
        getImgCode();
    });
    $('#kaptchaImage2').click(function () {
        getImgCode();
    });

    $('#kaptcha').bind({
        focus: function () {

        },
        blur: function () {
            var paramsTime = {
                kaptcha: this.value
            };
            $.ajax({
                url: "/kaptcha",
                data: paramsTime,
                type: "POST",
                success: function (data) {
                    if (data == "kaptcha_error") {
                        //显示验证码错误信息
                        show_validate_msg("#kaptcha", "error", "验证码错了");
                        //禁用按钮
                        $('#user_insert_btn').attr('disabled', "true");
                    } else {
                        //显示验证码正确信息
                        show_validate_msg("#kaptcha", "success", "验证码正确");
                        $('#user_insert_btn').removeAttr("disabled");
                    }

                }
            });
        }
    });
});
