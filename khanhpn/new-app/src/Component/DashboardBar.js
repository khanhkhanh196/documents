import React, {useState} from 'react';


import './css/categorydetail.css';
import {Button} from '@material-ui/core';
import { Redirect} from "react-router-dom";
import { withRouter } from "react-router";
import {AppBar,Toolbar,IconButton,Typography} from '@material-ui/core';
// import MenuIcon from '@material-ui/icons/Menu';
import { makeStyles } from '@material-ui/core/styles';

const DashboardBar = (props) => {
    const useStyles = makeStyles((theme) => ({
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

    const classes = useStyles();

    const [islogout, setIsLogOut]= useState(false);

    const signOut = () => {
        setIsLogOut(true);
        localStorage.clear("accessToken");
    
    };

    if (islogout) {
        return <Redirect exact to="/login" />;
    }
    else
    return(
        <div>
            <AppBar position="static">
            <Toolbar>
                <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                {/* <MenuIcon /> */}
                </IconButton>
                <Typography variant="h4" className={classes.title}>
                Dash Board
                </Typography>
                <Button style={{color:"white"}} onClick={signOut} >
                    Sign Out
                </Button>
            </Toolbar>
            </AppBar>          
        </div>
    );
}
export default withRouter(DashboardBar);