import React from "react";
import { Link, NavLink } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import Grid from "@material-ui/core/Grid";
import Icon from "@material-ui/core/Icon";
import ButtonLoading from "../Button/ButtonLoading";

const useStyles = makeStyles((theme) => ({
  root: {
    paddingTop: 15,
    paddingBottom: 15,
  },
  btnGroup: {
    textAlign: "right",
  },
  btnItem: {
    marginLeft: 15,
  },
}));

type Button = {
  onClick?: ((event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void) | undefined,
  classNames?: string,
  loading?: boolean,
  label?: string,
  link: string,
}

type Props = {
  buttons?: Button[],
  backBtn?: Button,
  title?: string
}

const HeadPage: React.FC<Props> = (props) => {
  const classes = useStyles();
  const { buttons, backBtn, title } = props;

  const switchButton = (item: Button, index: number) => {
    if (item.onClick) {
      return (
        <ButtonLoading
          className={classes.btnItem + " " + item.classNames}
          key={"head-btn-" + index}
          onClick={item.onClick}
          loading={item.loading}
        >
          {item.label}
        </ButtonLoading>
      );
    }
    return (
      <Link to={item.link || "/"} key={"head-btn-" + index}>
        <button className={classes.btnItem + " " + item.classNames}>
          {item.label}
        </button>
      </Link>
    );
  };

  return (
    <div className={classes.root}>
      {backBtn && (
        <NavLink to={backBtn.link} className="btn-back">
          <Icon className="icon">keyboard_arrow_left</Icon>
          {backBtn.label}
        </NavLink>
      )}

      <Grid container direction="row" justify="center" alignItems="center">
        <Grid item xs={12} sm={8}>
          <h3>{title}</h3>
        </Grid>
        <Grid item xs={12} sm={4} className={classes.btnGroup}>
          {buttons && buttons.map((item, index) => switchButton(item, index))}
        </Grid>
      </Grid>
    </div>
  );
};

export default HeadPage;
