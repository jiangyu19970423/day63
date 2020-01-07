let vm = new Vue({
    el: '#main-container',
    data: {
        area: '',
        oldParentId: ''//旧的父id
    },
    methods: {
        doUpdate: function () {
            axios({
                url: 'manager/area/doUpdate',
                method: 'post',
                data: this.area
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
        },
        selectIcon: function () {
            layer.open({
                type: 2,
                area: ['80%', '80%'],
                title: '区域修改',
                content: 'manager/area/awesome',
                end: () => {
                    this.area.icon = layer.icon;

                }
            })
        }
    },
    created: function () {
        //在vue对象创建的时候获取父窗口layer对象绑定的数据，放入当前app对象中
        this.area = parent.layer.obj;
        this.oldParentId = this.area.parentId;
    },
    mounted: function () {
        //choosen是一个下拉列表的插件:本质就是通过js将原来的下拉列表隐藏，并且额外添加div来实现
        $("#chosen-select").chosen({width: '100%', disable_search: true});//初始化chosen
    }
});


//取消跳转窗口
function fail() {
    let index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}