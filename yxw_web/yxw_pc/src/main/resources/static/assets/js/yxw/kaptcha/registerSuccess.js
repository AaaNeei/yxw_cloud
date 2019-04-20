
//使用匿名函数方法
function countDown(){
    var time = document.getElementById("Time");
    //alert(time.innerHTML);
    //获取到id为time标签中的内容，现进行判断
    if(time.innerHTML == 0){
        //等于0时清除计时
        window.location.href="/yxw/login";
    }else{
        time.innerHTML = time.innerHTML-1;
    }
}
//1000毫秒调用一次
window.setInterval("countDown()",1000);
