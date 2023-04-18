import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Form } from 'react-bootstrap';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

type Props = {

}
const AddNewCategoryPopupBody: React.FC = (props: Props) => {
    return (
        <div className="container">
            <Form>
            <Form.Row>
                <Form.Group as={Col}  controlId="">
                    <Form.Label style={{display:"inline-flex"}}><h6>Tên loại</h6><span style={{color:"red",marginLeft:"5px"}}>*</span></Form.Label>
                    <Form.Control type="text" placeholder="" />
                </Form.Group>

                <Form.Group as={Col} controlId="">
                    <Form.Label><h6>Mã</h6></Form.Label>
                    <Form.Control type="text" placeholder="" />
                </Form.Group>
            </Form.Row>
                <Form.Group controlId="formBasicPassword">
                    <Form.Label><h6>Ghi chú</h6></Form.Label>
                    <Form.Control as="textarea" rows={3} placeholder="" />
                </Form.Group>

            </Form>
        </div>
        
    );
}
export default AddNewCategoryPopupBody;