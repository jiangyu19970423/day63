let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,//开启简单数据模式支持
                        // "pId"Key:""pId""
                        pIdKey: "parentId"
                    }
                },
                callback: {
                    onClick: this.onClick,//如果设置this.xxx  methods对象还没有，不能绑定上
                    beforeEditName: this.beforeEditName
                },
                edit: {
                    enable: true
                },

                view: {
                    addHoverDom: this.addHoverDom,
                    removeHoverDom: this.removeHoverDom
                }
            },
            pageInfo: '',
            nodes: [],
            treeObj: {},
            params: {
                pageNum: '',
                pageSize:
                    '',
                areaName:
                    '',//默认值，让下拉出现的时候默认被选中
                name:''
            }
            ,

        }
    },
    methods: {
        selectPage: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;

            axios({
                url: 'manager/office/toList',
                method: 'post',
                data: this.params
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url: 'manager/office/toUpdate',
                params: {oid: id}
            }).then(response => {
                layer.obj = response.data;
                layer.open({
                    type: 2,
                    area: ['80%', '80%'],
                    title: '区域修改',
                    content: 'manager/office/toUpdatePage',
                    end: () => {
                        this.selectPage(1, 5);
                        this.initTree();

                    }
                })
            }).catch(error => {
                layer.msg(error.message);
            })
        },
        initTree: function () {//初始化ztree
            //获取nodes
            axios({
                url: 'manager/office/list'
            }).then(response => {
                this.nodes = response.data;//   this.setNodes(.....)
                this.nodes[this.nodes.length]={
                    "id": 0,
                    "name": "所有机构"
                };
                this.treeObj = $.fn.zTree.init($("#treeMenu"), this.setting, this.nodes);
                console.log(this.treeObj);

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        onClick: function (event, treeId, treeNode) {
            this.params.aid = treeNode.id;
            this.selectPage(1, 5)
            // console.log(11)
        },
        beforeEditName: function (treeId, treeNode) {//结合开启修改节点按钮、点击修改按钮事件回调处理更新节点弹框
            this.toUpdate(treeNode.id);
            return false;//阻止进入修改节点状态
        },
        addHoverDom: function (treeId, treeNode) {
            let aObj = $("#" + treeNode.tId + "_a");
            if ($("#treeMenu_" + treeNode.id + "_add").length > 0) return;
            //<span class="button edit" id="treeMenu_3_edit" title="rename" treenode_edit="" style=""></span>
            let editStr = `<span class="button add" id="treeMenu_${treeNode.id}_add" title="add"  style=""></span>`;
            aObj.append(editStr);
            let span = $("#treeMenu_" + treeNode.id + "_add");
            if (span) span.bind("click", function () {
                alert("diy Button for " + treeNode.name);
            });
        },
        removeHoverDom: function (treeId, treeNode) {
            $("#treeMenu_" + treeNode.id + "_add").unbind().remove();
        },
        deleteArea: function (id) {
            console.log(id);
            axios({
                url: 'manager/area/delete',
                params: {id: id}
            }).then(response => {
                this.selectPage(1, 5);
                layer.msg("删除成功");
            }).catch(error => {
                layer.msg(error);
            })
        }
    },

    created: function () {
        this.selectPage(1, 5);
    },
    mounted: function () {
        this.initTree();
    }

});