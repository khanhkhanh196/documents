import React, { useEffect, useState, useContext } from 'react';
import { useHistory, Link } from "react-router-dom";
import { deleteCategoryByID, getCategory,getCategoryByID,axios_config} from '../../endpoints/Category';

import CategoryItem from './CategoryItem';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/categorylist.css';
import Pagination from "react-js-pagination";
import 'react-bootstrap';
import { makeStyles } from '@material-ui/core/styles';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

import { AuthContext } from '../../context/auth';

const CategoryList = () => {
    const useStyles = makeStyles((theme:any) => ({
        root: {
          flexGrow: 1,
        },
        menuButton: {
          marginRight: theme.spacing(2),
        },
        title: {
          flexGrow: 1,
        },
      }));

    useStyles();

    const [category,setListCategories] = useState([]);
    const [page,setPage] = useState(0);
    const [sizePage,setSizePage] = useState(5);
    const history = useHistory();
    const value = useContext(AuthContext);
    const [flag,setFlag] = useState(0);
    const [totalPage,setTotalPage]  = useState<number>();

    useEffect(() => {
        const fetchCategories = async() => {
            let categories = await getCategory(page,sizePage);
            console.log(categories)
            setListCategories(categories.content)
            setTotalPage(categories.totalElements)
        }
        fetchCategories();
    },[flag]);

    const onPageChange = (activePage: number) => {
      const fetchCategories = async() => {
        let categories = await getCategory(activePage,sizePage);
        console.log(categories)
        setListCategories(categories.content)
        setTotalPage(categories.totalElements)
    }
    fetchCategories();
    }
    
    const deleteCategory = async (id: number) =>{
      console.log(value.authTokens);
      axios_config(value.authTokens);
      const res = await deleteCategoryByID(id);
      console.log(res);
      let i = 0
      setFlag(++i);
    }

    const showCategoryDetail = (id: number) => {
      history.push({
        pathname: `/detail/${id}`,
        });
    }

    let listCategory = 
    category
    .map((x:any) => <CategoryItem className="categoryitem" data={x} key={x.id} clickMe={showCategoryDetail} deleteMe={deleteCategory}/>)
 
        return(
            <div>
                
                <div className="container category-container">
                    <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                          <th className="categoryId">#</th>
                          <th className="categoryName">Category Name</th>
                          
                          <td className="categoryAction">
                            <Button >
                              <Link to={{pathname: "/category", state:{category:[],isUpdated:false}}}>ADD</Link>
                            </Button>
                          </td>
                        </tr>
                    </thead>
                    </Table>
                    {listCategory}
                    
                </div>
                <Pagination totalItemsCount={totalPage} activePage={page} itemsCountPerPage={sizePage} pageRangeDisplayed={5}
                onChange={(x:number)=>{setPage(x);onPageChange(x-1)}} onChangeSize={(x:number)=>{setSizePage(x);setPage(1)}}/>
            </div>
            
        );
}
export default CategoryList;