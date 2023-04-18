import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

class CategoryItem extends Component {
    render(){
        console.log(this.props.data);
        return(
           <div className="container">
               <div className="category" onClick={this.props.clickMe} >
                   <ul>
                       <li >{this.props.data.categoryName} </li>
                       
                   </ul>
                   
                </div>
           </div> 
        )
    }
}

export default CategoryItem;