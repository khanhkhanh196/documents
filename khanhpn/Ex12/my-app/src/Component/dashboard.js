import React, {Component} from 'react';
import CategoryList from './CategoryList';
import {getCategoryByID} from '../endpoints/Category';
import CategoryDetail from './CategoryDetail';
class Dashboard extends Component {
    state = {
        categoryDetail :[],
        theId:'',
     }


    fetchCategoryById = async (id) =>{
        const payload = await getCategoryByID(id);
        console.log( payload)
        this.setState({categoryDetail:payload})
        console.log("category detail  " + this.state.categoryDetail);
    }


    showCategoryDetail(id){
        this.setState({theId:id})
        // console.log(this.state.theId)
        this.fetchCategoryById(id)
    }

    render(){
        return(
            <div>
                <div className="dashboard"><h3>Dash Board</h3></div>
                <div className="greeting"><h2>Hello {this.props.name}</h2></div>
                <div className="container">
                    <div class="row">
                        <div className="col-lg-6">
                            <CategoryList name={this.props.username} showCategoryDetail={this.showCategoryDetail.bind(this)}/> 
                        </div>
                        <div className="col-lg-6">
                            <CategoryDetail className="category-detail" detail={this.state.categoryDetail}/>
                        </div>
                    </div>
                    
                </div>
            </div>
        );
    }
}
export default Dashboard;