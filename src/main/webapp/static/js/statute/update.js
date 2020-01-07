let vm = new Vue({
    el: '#main-container',
    data: {
        statute: '',
        myConfig:{
            UEDITOR_HOME_URL:'static/ueditor/',  //默认ueditor加载资源的前缀路径
            charset:"utf-8",
            serverUrl:'doExec'  //  后端统一接口路径   /ueditor/doExec
        }
    },
    methods: {
        doUpdate: function () {
            axios({
                url: 'manager/statute/doUpdate',
                method: 'post',
                data: this.statute
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
        this.statute = parent.layer.obj;
    },
    components:{//引入vue的富文本编辑器组件
        VueUeditorWrap
    }
});


//取消跳转窗口
function fail() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}