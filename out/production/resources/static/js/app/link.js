var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            stackCategory: $('#stackCategory').val(),
            description: $('#description').val(),
            postNum: $('#postNum').val(),
            githubRepo: $('#githubRepo').val(),
            importance: $('#importance').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/link',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('링크가 등록되었습니다.');
            window.location.href = '/link?page=1&type=title&keyword=';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            stackCategory: $('#stackCategory').val(),
            description: $('#description').val(),
            postNum: $('#postNum').val(),
            githubRepo: $('#githubRepo').val(),
            importance: $('#importance').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/link/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('링크가 수정되었습니다.');
            window.location.href = '/link?page=1&type=title&keyword=';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/link/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('링크가 삭제되었습니다.');
            window.location.href = '/link?page=1&type=title&keyword=';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();