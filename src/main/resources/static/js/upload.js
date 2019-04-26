
//点击事件
function submitForm() {
    var formData = new FormData($("#form")[0]);  //重点：要用这种方法接收表单的参数
    $.ajax({
        url : "fileUploadController",
        type : 'POST',
        data : formData,
        // 告诉jQuery不要去处理发送的数据
        processData : false,
        // 告诉jQuery不要去设置Content-Type请求头
        contentType : false,
        async : false,
        success : function(data) {
            if(data){
                $("#url").val(data);
                alert("编译完成!请下载!")
            }
        }
    });
}

//下载
function downUrl() {
    window.location.href=$("#url").val();
}