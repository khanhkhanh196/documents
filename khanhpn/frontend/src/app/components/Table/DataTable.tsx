import React,{useEffect, useState} from "react";
import { Link } from "react-router-dom";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableFooter from "@material-ui/core/TableFooter";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import Tooltip from "@material-ui/core/Tooltip";
import DeleteIcon from "@material-ui/icons/Delete";
import Moment from "react-moment";
import Header from "../HeadPage/Header";
import Pagination from "./Pagination";
import ColumnType from "./ColumnType";
import "./style/DataTable.css";

type Props = {
  columns: ColumnType[],
  rows?: Array<any>,
  createLink?: string,
  updateLink?: string,
  titlePage?: string,
  page: number,
  rowsPerPage: number,
  totalRows: number,
  onChangePage: (newPage: number) => void,
  onChangeRowsPerPage: (rowsPerPage: number) => void,
  onDeleteRow: (id: number) => void,
}

const DataTable: React.FC<Props> = (props) => {
  const {
    columns, rows, createLink, updateLink,
    titlePage, page, rowsPerPage, totalRows,
    onChangePage, onChangeRowsPerPage, onDeleteRow,
  } = props;
  let [checked,setCheck] = useState<number>(0);
  const [checkedAll,setCheckAll] = useState<boolean>(false);
  const selectedItem = new Set();
  
  let buttons;
  if (createLink) {
    buttons = [
      {
        link: createLink,
        label: "Tạo mới",
        classNames: "btn btn-primary",
      },
    ];
  }

  useEffect(()=>{
    if(rows) {
      rows.map((row, index) => {
        row["isCheck"] = false;
      })
    }
    console.log("reloaded")
  },[])

  const handleCellSelect = (id:number,event:any) => {
      if(rows) {
        rows.forEach((row) => {
          if(row.id === id) {
            console.log(row.isCheck);
            if(row.isCheck === true) {
              row.isCheck = false;
              setCheck(++checked);
            } else if(row.isCheck === false) {
              row.isCheck = true;
              setCheck(++checked);
            }
          }
        })
      }

  }

  const handleThCheckBox = () => {
    if(checkedAll === false) {
      setCheckAll(true)
      if(rows) {
        rows.forEach(item => {
          item.isCheck = true
        });
      }
    } else if (checkedAll === true) {
      setCheckAll(false)
      if(rows) {
        rows.forEach(item => {
          item.isCheck = false
        });
      }
      
    }
    
  }


  const getCell = (row: any, column: ColumnType) => {
    const value = row[column.field];
    switch (column.type) {
      case "image":
        if (value) {
          return (<td key={column.field} align={column.align} className="table-cell img">
            <Link to={updateLink + row.id}><img src={value} alt="Ảnh SP" className="" /></Link>
          </td>)
        }
        return (<td key={column.field} align={column.align} className="table-cell img">
          <Link to={updateLink + row.id}><img src={value} alt="Ảnh SP" className="" /></Link>
        </td>);
      case "link":
        return (<td key={column.field} align={column.align} className="table-cell">
          <Link to={updateLink + row.id}>{value}</Link>
        </td>)
      case "date":
        return (<td key={column.field} align={column.align} className="table-cell">
          <Moment date={value} format={column.format} />
        </td>)
      default:
        return (<td key={column.field} align={column.align} className="table-cell">
          {value}
        </td>)
    }
  };

  return (
    <>
      <Header title={titlePage} buttons={buttons} />
      <div className="paper">
        <div className="row">
          <div className=" col-12">
            <Table className="table-custom">
              <TableHead>
                <TableRow>
                  <th className="table-cell" >
                    <input type="checkbox"
                    style={{ 
                    marginTop:"5px",
                    marginLeft:"13px" }} 
                    onChange={() => handleThCheckBox()}
                    />
                  </th>
                  {columns.map((column) => {
                    return (
                    <th
                      key={column.field}
                      align={column.align}
                      className="table-cell"
                      style={{ minWidth: column.minWidth }}
                    >
                      {column.label}
                    </th>)
                  })}
                  {updateLink && (
                    <th align="center" key="table-actions" className="table-cell">
                      Chức năng
                    </th>
                  )}
                </TableRow>
              </TableHead>
              {rows && (
                <>
                  <TableBody>
                    {rows.map((row, index) => {
                      return (
                        <TableRow hover key={index}>
                            <TableCell
                                align="center"
                                className="table-cell"
                            >
                              <input type="checkbox"
                              style={{ marginTop:"7px" }}
                              onChange={(event) => handleCellSelect(row.id,event)} 
                              checked={row.isCheck} />
                            </TableCell>
                          {columns.map((column) => {
                            return getCell(row, column);
                          })}
                          
                          {onDeleteRow && (
                            <TableCell
                              key={"table-actions-delete-" + index}
                              align="center"
                              className="table-cell"
                            >
                              <Tooltip
                                title="Xóa"
                                onClick={(e) => onDeleteRow(row.id)}
                              >
                                <DeleteIcon className="text-danger" />
                              </Tooltip>
                            </TableCell>
                          )}
                        </TableRow>
                     
                      );
                    })}
                  </TableBody>
                  <TableFooter>
                    <TableRow>
                      <Pagination
                        columns={columns}
                        page={page}
                        rowsPerPage={rowsPerPage}
                        totalRows={totalRows}
                        onChangePage={onChangePage}
                        onChangeRowsPerPage={onChangeRowsPerPage}
                        rowsPerPageOptions={[20, 50, 100]}
                      />
                    </TableRow>
                  </TableFooter>
                </>
              )}
            </Table>
          </div>
        </div>
      </div>
    </>
  );
};

export default DataTable;
