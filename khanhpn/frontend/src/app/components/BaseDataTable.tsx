import React, { useEffect, useState, useContext, useCallback } from "react";
import DataTable from "./Table/DataTable";
import Popup from "./Popup/Popup";
import ColumnType from "./Table/ColumnType";
import Service from "../../services/base/Service";
import AppContext from "../contexts/AppContext";

type Props = {
    service: Service,
    baseURL: string,
    columns: ColumnType[],
    titlePage: string,
    confirmDelete: {
        title: string,
        message: string,
        messageSuccess: string
    },
    createLink?: string,
    updateLink?: string
}

const BaseDataTable: React.FC<Props> = (props) => {
    const appCtx = useContext(AppContext);
    const { openNotifier, levels } = appCtx;

    const {
        service,
        confirmDelete,
        titlePage,
        columns,
        baseURL,
        createLink,
        updateLink
    } = props;
    const [showPopup, setShowPopup] = useState<boolean>(false);
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
    const [readyUpdate, setReadyUpdate] = useState(true);

    const [domains, setDomains] = useState<{
        data: Array<any>,
        totalElements: number,
        totalPages: number,
    }>();
    const showPopupDelete = (idItem: number) => {
        setIdItemFocus(idItem);
        setShowPopup(true);
    };
    const closePopupDelete = () => {
        setShowPopup(false);
        setIdItemFocus(undefined);
    };
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
    const handDeleleRow = () => {
        const id = idItemFocus;
        if (!id) {
            return;
        }
        deleteRow(id);
    };

    const deleteRow = async (id: number) => {
        const response = await service.delete(id);
        if (!response) {
            openNotifier(
                true,
                "Hệ thống đang bận, vui lòng thử lại sau",
                levels.warn
            );
            return;
        }
        if (response && response.status !== 200) {
            openNotifier(true, response.data.message, levels.warn);
            return;
        }
        openNotifier(true, confirmDelete.messageSuccess, levels.success);
        setReadyUpdate(true);
    };

    const resetList = useCallback(async () => {
        const response = await service.getList(query.page, query.size, query);

        if (response && response.status === 404) {
            openNotifier(true, response.message, levels.warn);
            setDomains(undefined);
            return;
        }

        if (!response || response.status !== 200) {
            openNotifier(true, "Lỗi hệ thống", levels.warn);
            setDomains(undefined);
            return;
        }

        const responseData = response.data;
        if (!responseData) {
            return
        }
        setDomains({
            data: responseData.data,
            totalElements: responseData.total_elements,
            totalPages: responseData.total_pages,
        });
    }, [openNotifier, levels, query, service]);

    useEffect(() => {
        if (readyUpdate) {
            resetList();
            setReadyUpdate(false);
        }
    }, [readyUpdate, resetList]);
    useEffect(() => {
        setReadyUpdate(true);
    }, [query]);

    return (
        <>
            <Popup
                buttonSubmit={{ label: "Xóa", onClick: handDeleleRow, classname: "btn-danger" }}
                title={confirmDelete.title}
                show={showPopup}
                onClose={() => closePopupDelete()}
                body={confirmDelete.message}
            />
            <DataTable
                rows={domains && domains.data}
                columns={columns}
                createLink={createLink || `/${baseURL}/create`}
                updateLink={updateLink || `/${baseURL}/`}
                onDeleteRow={(idItem) => showPopupDelete(idItem)}
                page={query.page - 1}
                rowsPerPage={query.size}
                titlePage={titlePage}
                totalRows={domains ? domains.totalElements : 0}
                onChangePage={handleChangePage}
                onChangeRowsPerPage={handleChangeRowsPerPage}
            />
        </>
    );
}

export default BaseDataTable;