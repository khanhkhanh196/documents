import React, {useEffect, useState, useContext }  from 'react';
import Form from "react-bootstrap/Form";
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import '../css/categoryaddnew.css';
import {addNewCategory,updateCategory, axios_config} from '../../endpoints/Category';
import {Link } from 'react-router-dom';
import DashboardBar from '../DashboardBar';
import { AuthContext } from '../../context/auth';

const CategoryAddNew= (props) =>{

    const [id,setId] = useState('');
    const [categoryName,setCategoryName]= useState('');
    const [ categoryCode, setCategoryCode]= useState('');
    const [categoryDes,setCategoryDes]= useState('');
    const [isUpdated,setIsUpdated]= useState(false);
    const value = useContext(AuthContext);

    useEffect(() => {
        setIsUpdated(props.location.state.isUpdated);
        setId(props.location.state.category.id);
        setCategoryName(props.location.state.category.categoryName);
        setCategoryCode(props.location.state.category.categoryCode);
        setCategoryDes(props.location.state.category.categoryDescription);
    },[]);

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(isUpdated);
        if(isUpdated === true) {
            const category = {
                id: id,
                categoryName: categoryName,
                categoryCode: categoryCode,
                categoryDescription: categoryDes
            }
            // add token to header
            axios_config(value.authTokens);
            const response = async () => {
                const res = await updateCategory(id, category).catch(error => alert('Updated Failed'));
                console.log(res);
                if(res != null && res.status === 200) {
                    alert('Updated Success');
                }
            } 
            response();
        } else if(isUpdated === false){
            const category = {
                categoryName: categoryName,
                categoryCode: categoryCode,
                categoryDescription: categoryDes
            }
            // add token to header
            axios_config(value.authTokens);
            const response = async () => {
                const res =  await addNewCategory(category).catch(error => alert('Add Failed'));
                console.log(res);
                if(res != null && res.status === 200) {
                    alert('Added Success');
                }
            } 
            response();
           
        }
    }

    return(
    <div className="addnew">
        <DashboardBar />
        <div className="container add-form">
        <Form onSubmit={handleSubmit}>
            <Form.Group size="lg" as={Row}>
                <Form.Label column sm={3}>Category Code</Form.Label>
                <Col sm={9}>
                    <Form.Control
                    autoFocus
                    placeholder="Category Code Must Start With C"
                    value={categoryCode}
                    onChange = {(newValue) => setCategoryCode(newValue.target.value)}
                    />
                </Col>
            </Form.Group>
            <Form.Group size="lg" as={Row}>
                <Form.Label column sm={3}>Category Name</Form.Label>
                <Col sm={9}>
                    <Form.Control
                    autoFocus
                    value={categoryName}
                    onChange = {(newValue) => setCategoryName(newValue.target.value)}
                    />
                </Col>
            </Form.Group>
            <Form.Group size="lg" as={Row}>
                <Form.Label column sm={3}>Category Description</Form.Label>
                <Col sm={9}>
                    <Form.Control
                    as="textarea"
                    rows={5}
                    autoFocus
                    value={categoryDes}
                    onChange = {(newValue) => setCategoryDes(newValue.target.value)}
                    />
                </Col>
            </Form.Group>
            <Form.Group size="lg" as={Row}>
                <Button variant="primary">
                    <Link to="/admin">Back To Dashboard</Link> 
                </Button>

                <Button className="submit-button" variant="primary" type="submit">
                    Submit
                </Button>
            </Form.Group>
                
            </Form>
        </div>
        
    </div>
        
    );
}

export default CategoryAddNew;