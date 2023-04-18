import React, { useState } from 'react';
import BaseDataTable from '../../components/BaseDataTable';
import ColumnType from "../../components/Table/ColumnType";
import FakeService from '../../../services/FakeService';

const DomainsPage: React.FC = () => {

    const [columns] = useState<ColumnType[]>([
        { field: "id", label: "ID" },
        { field: "image_url", type: "image" },
        { field: "name", label: "Sản phẩm", type: "link" },
        {
            field: "code",
            label: "SKU",
        },
        {
            field: "category_name",
            label: "Danh mục",
        },
        {
            field: "quantity",
            label: "Có thể bán",
        },
        {
            field: "created_on",
            label: "Ngày khởi tạo",
            type: "date",
            format: "DD/MM/YYYY",
        },
        {
            field: "modified_on",
            label: "Ngày cập nhật",
            type: "date",
            format: "DD/MM/YYYY",
        },
    ]);
    const confirmDelete = {
        title: "Xóa sản phẩm",
        message: "Thao tác này sẽ xóa sản phẩm bạn đã chọn. Thao tác này không thể khôi phục.",
        messageSuccess: "Xóa sản phẩm thành công"
    }
    const titlePage = "Danh sách sản phẩm";

    return (
        <BaseDataTable
            baseURL="demo"
            service={new FakeService()}
            columns={columns}
            confirmDelete={confirmDelete}
            titlePage={titlePage}
        />
    )
}

export default DomainsPage;