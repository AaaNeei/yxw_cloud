count = 1;

function scenicSpot(id) {

    if (id == 'minus') {
        if (count == 1) {
            alert("亲,保留一个吧!");
        } else {
            $('#scenicSpot' + count).hide();
            document.getElementById("scenicSpot_minus").style.marginTop = Math.ceil(count / 2) * 200 + 'px';
           // document.getElementById("scenicSpot_add").style.marginTop =  Math.ceil(count / 2) * 200 + 'px';
            count--;
        }
    }
    if (id == 'add') {
        count++;
        if (count > 12) {
            alert("亲,够了吧!");
        } else {
            $('#scenicSpot' + count).show();
            document.getElementById("scenicSpot_minus").style.marginTop = Math.ceil(count / 2) * 200 + 'px';
           // document.getElementById("scenicSpot_add").style.marginTop =  Math.ceil(count / 2) * 200 + 'px';
        }
    }

}