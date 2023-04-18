import React from 'react';
import Table from 'react-bootstrap/Table';
import '../css/categorydetail.css';
import {Link, Redirect} from 'react-router-dom';
import DashboardBar from '../DashboardBar';
import Button from 'react-bootstrap/Button';
const CategoryDetail = (props) => {

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
                            <td className="category-id">{props.location.state.category.id}</td>
                            <td className="category-code">{props.location.state.category.categoryCode}</td>
                            <td className="category-name">{props.location.state.category.categoryName}</td>
                            <td className="category-des"><p>{props.location.state.category.categoryDescription}</p></td>
                            <td><Link to={{ 
                                pathname: "/category", 
                                state: {
                                    category: props.location.state.category,
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