import React from 'react';
import DashboardBar from './DashboardBar';
import CategoryList from "./CategoryComponent/CategoryList";
const AdminPage = (props) => {
    return(
        <div>
            <DashboardBar/>
            <div className="container">
                    <CategoryList  /> 
            </div>
        </div>
    );
}
export default AdminPage;