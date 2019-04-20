$(function () {
    //比较简洁，细节可自行完善
    $('#uploadSubmit').click(function () {
        var data = new FormData($('#uploadForm')[0]);
        $.ajax({
            url: 'xxx/xxx',
            type: 'POST',
            data: data,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.status){
                    console.log('upload success');
                }else{
                    console.log(data.message);
                }
            },
            error: function (data) {
                console.log(data.status);
            }
        });
    });

});