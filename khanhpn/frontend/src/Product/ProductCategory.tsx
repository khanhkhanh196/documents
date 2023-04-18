import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableFooter from "@material-ui/core/TableFooter";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import Tooltip from "@material-ui/core/Tooltip";
import Button from 'react-bootstrap/Button';
import AddNewCategoryPopupBody from './AddNewCategoryPopupBody'
import Popup from '../app/components/Popup/Popup';
import ColumnType from "../app/components/Table/ColumnType";
import DataTable from "../app/components/Table/DataTable"
type Props = {

}

const ProductCategory = (props: Props) => {
    const [showPopup, setShowPopup] = useState<boolean>(false);
    const [showPopupDeleted, setShowPopupDelete] = useState<boolean>(false);
    const [idItemFocus, setIdItemFocus] = useState<number>();
    const [query, setQuery] = useState<{
        page: number,
        size: number,
        name: string
    }>({
        page: 1,
        size: 20,
        name: "",
    });
    
    const [columns] = useState<ColumnType[]>([
        { field: "category_name", label: "Loại sản phẩm" },
        { field: "category_code", label: "Mã" },
        { field: "description", label: "Ghi chú"},
        {
            field: "created_on",
            label: "Ngày khởi tạo",
            type: "date",
            format: "DD/MM/YYYY",
        }
    ]);
    
    const [domains, setDomains] = useState<{
        data: Array<any>,
        totalElements: number,
        totalPages: number,
    }>();

    const handleChangePage = (newPage: number) => {
        setQuery({
            ...query,
            page: newPage,
        });
    };
    const handleChangeRowsPerPage = (newSize: number) => {
        setQuery({
            ...query,
            size: newSize,
            page: 1,
        });
    };

    const closePopupAddCategory = () => {
    };

    const handAddCategory= () => {
    
    };
    const showPopupDelete = (idItem: number) => {
        setIdItemFocus(idItem);
        setShowPopupDelete(true);
    };

    const titlePage = "Loại sản phẩm"
    return (
        <div className="container">
             <div>
                <DataTable
                    rows={domains && domains.data}
                    columns={columns}
                    createLink={undefined}
                    updateLink={undefined}
                    onDeleteRow={(idItem) => showPopupDelete(idItem)}
                    page={query.page - 1}
                    rowsPerPage={query.size}
                    titlePage={titlePage}
                    totalRows={domains ? domains.totalElements : 0}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                />
            </div>
        <div>
            <Popup
                buttonSubmit={{ label: "Thêm mới", onClick: handAddCategory, classname: "btn-primary" }}
                title={"Thêm mới loại sản phẩm"}
                show={showPopup}
                onClose={() => closePopupAddCategory()}
                body={<AddNewCategoryPopupBody/>} />
        </div>
        </div>
       
    );
}
export default ProductCategory;