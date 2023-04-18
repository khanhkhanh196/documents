import React, {Component} from 'react';

import { getCategory } from '../endpoints/Category';
import CategoryItem from './CategoryItem';
import 'bootstrap/dist/css/bootstrap.min.css';
import './css/categorylist.css';
import Pagination from "react-js-pagination";
class CategoryList extends Component {
    state = {
        category: [],
        page:1,
        sizePage:5
    }

    componentDidMount() {
        const fetchCategory = async () => {
            let payload = await getCategory();
            console.log(typeof({payload}));
            console.log({payload});
            this.setState({category: payload});
        } 
        fetchCategory();
    }

    


    render(){
        return(
            <div>
                <div className="container">
                    {this.state.category.map((x) => <CategoryItem className="categoryitem" data={x} key={x.id} clickMe={()=>this.props.showCategoryDetail(x.id)}/>) }
                    {console.log(this.state.category)}
                </div>
                
            </div>
        );
    }
}

export default CategoryList;