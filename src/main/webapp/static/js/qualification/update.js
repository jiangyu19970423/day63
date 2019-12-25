let vm = new Vue({
    el: '#main-container',
    data: {
        qualification: '',
        oid: ''
    },
    methods: {
        doUpdate: function (check) {
            this.qualification.check=check;
            axios({
                url: 'manager/qualification/doUpdate',
                method: 'post',
                data: this.qualification
            }).then(response => {
                //更新成功，关闭当前窗口    在父窗口上显示提示信息
                if (response.data.success) {
                    let index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                    parent.layer.msg(response.data.msg);
                    return;
                }
                layer.msg(response.data.msg);
            }).catch(error => {
                layer.msg(error)
            })
        }
    },
    created: function () {
        //在vue对象创建的时候获取父窗口layer对象绑定的数据，放入当前app对象中
        this.qualification = parent.layer.obj;
        this.oid = parent.layer.oid;
    }
});


//取消跳转窗口
function fail() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}