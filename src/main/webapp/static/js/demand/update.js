let vm = new Vue({
    el: '#main-container',
    data: {
        demand:{

        }
    },
    methods: {
        doUpdate: function () {
            axios({
                url: 'manager/demand/doUpdate',
                method: 'post',
                data: this.demand
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
        this.demand = parent.layer.obj;
    }
});


//取消跳转窗口
function fail() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}