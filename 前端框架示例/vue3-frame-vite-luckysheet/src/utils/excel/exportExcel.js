/**
 * 转换blob
 * @author niuluda
 * @params resp 响应对象 filename 文件名 type:类型
 */
export default {
    downloadDataNoBody: function (resp, filename, type) {
        const blob = new Blob([resp], {type: type ? type : 'application/vnd.ms-excel'});
        if ('msSaveOrOpenBlob' in navigator) {
            window.navigator.msSaveOrOpenBlob(blob)
        } else {
            const url = window.URL || window.webkitURL
            const objectUrl = url.createObjectURL(blob);
            const link = document.createElement('a')
            link.href = objectUrl;
            link.download = filename ? filename : '公网通信费用管理平台_结算审核数据单v1';
            link.click();
            url.revokeObjectURL(objectUrl);
        }
    },
    downloadZip: function (res,filename) {
        const url=window.URL.createObjectURL(new Blob([res]));
        const link = document.createElement('a');
        link.href=url;
        link.setAttribute('download','公网通信费用管理平台_结算审核登记单v1'+'.zip')
        document.body.appendChild(link)
        link.click()
        document.body.removeChild(link)
    }
}