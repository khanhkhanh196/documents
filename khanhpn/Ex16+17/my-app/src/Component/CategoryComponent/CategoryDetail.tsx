import React, {useEffect,useState} from 'react';
import Table from 'react-bootstrap/Table';
import '../css/categorydetail.css';
import {Link, } from 'react-router-dom';
import DashboardBar from '../DashboardBar';
import Button from 'react-bootstrap/Button';
import { getCategoryByID} from '../../endpoints/Category';
import { useParams } from "react-router";
type Category = {
    id: number,
    categoryName: string;
    categoryCode: string,
    categoryDescription: string
}

interface Props {
    props: Category;
}
type params =  {
    id:string;
}

const CategoryDetail = (props:Props) => {

    const params = useParams<params>();
    const [categoryDetail,setCategoryDetail] = useState<Category>();

    useEffect(()=>{
        const theId = parseInt(params.id);
        const fetchCategoryById = async (id: number) =>{
            const payload = await getCategoryByID(id);
            console.log(payload);
            setCategoryDetail(payload);
        }
        fetchCategoryById(theId);        
    },[]);

    if(!categoryDetail) return (<div><h2>Category Not Found</h2></div>);
    return(
        <div>
            <DashboardBar />
            <div className="container category-detail">
                <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                            <th className="category-id">#</th>
                            <th className="category-code">Category Name</th>
                            <th className="category-name">Category Code</th>
                            <th className="category-des">Category Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td className="category-id">{categoryDetail.id}</td>
                            <td className="category-code">{categoryDetail.categoryCode}</td>
                            <td className="category-name">{categoryDetail.categoryName}</td>
                            <td className="category-des"><p>{categoryDetail.categoryDescription}</p></td>
                            <td><Link to={{ 
                                pathname: "/category", 
                                state: {
                                    category: categoryDetail,
                                    isUpdated:true
                                }
                            }} >UPDATE</Link></td>
                        </tr>
                    </tbody>
                </Table>
                <Button variant="primary">
                    <Link to="/admin">Back To Dashboard</Link> 
                </Button>
            </div>
        </div>
    );
}

export default CategoryDetail;