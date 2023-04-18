import React from 'react';

class CategoryDetail extends React.Component {
    render(){
        return(
            <div className="container">
                <div className="category-detail">
                    <ul>
                        <li>Category ID :{this.props.detail.id}</li>
                        <li>Category Code: {this.props.detail.categoryCode}</li>
                        <li>Category Name:{this.props.detail.categoryName}</li>
                        <li>Category Desciption <p>{this.props.detail.categoryDescription}</p></li>
                    </ul>
                </div>
            </div>
        );
    }
}

export default CategoryDetail;