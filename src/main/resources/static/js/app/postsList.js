var main = {
    init : function () {
        var _this = this;
        $('#btn-keyword').on('click', function () {
            _this.keyword();
        });
    },
    keyword : function () {
        var data = {
            type: $('#type').val(),
            keyword: $('#keyword').val(),
        };
        $.ajax({
            type: 'GET',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/posts/save';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

main.init();