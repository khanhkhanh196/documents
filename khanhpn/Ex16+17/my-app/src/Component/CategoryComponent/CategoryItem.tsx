import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Table from 'react-bootstrap/Table';
import { Link} from "react-router-dom";
import Button from 'react-bootstrap/Button';
import '../css/categorylist.css';

type Category = {
    id: number,
    categoryName: string;
    categoryCode: string,
    categoryDescription: string
}

type propsCategoryItem = {
    deleteMe: any;
    clickMe: any;
    data: Category;
    className:String;
}


const CategoryItem = (props: propsCategoryItem) =>  {

    const deleteButtonClick = () =>{
        
        var x = window.confirm("Are you sure you want to delete?");
            if (x){
                const deleteD = () => props.deleteMe(props.data.id);
                deleteD();
                return true;
            }
            else
                return false;
    }

    const detailButtonClick = () => {
        const detail = () => props.clickMe(props.data.id);
        detail();
    }

        // console.log(props.data);
    return(
        <div className="container " >
            <div className="category" >
            <Table striped bordered hover variant="dark">
                <tbody>
                    <tr>
                        <td className="categoryId">{props.data.id}</td>
                        <td className="categoryName">{props.data.categoryName}</td>
                        <td className="categoryAction" ><Button onClick={deleteButtonClick}><Link to="/admin">DELETE</Link></Button></td>
                        <td className="categoryAction" ><Button onClick={detailButtonClick} >DETAIL</Button></td>
                    </tr>
                </tbody>
            </Table>
            </div>
        </div> 
    )
}

export default CategoryItem;