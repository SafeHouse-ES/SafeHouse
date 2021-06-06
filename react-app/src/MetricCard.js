import React, {useEffect, useState} from 'react';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Title from './Title';

function preventDefault(event) {
  event.preventDefault();
}

const useStyles = makeStyles({
  depositContext: {
    flex: 1,
  },
});

export default function MetricCard(props) {
  const classes = useStyles();

  let [time, setTime] = React.useState('');

  React.useEffect(() => {
    setTime(new Date().toLocaleTimeString())
    
    const interval=setInterval(()=>{
      setTime(new Date().toLocaleTimeString())
    },5000)

    return()=>clearInterval(interval)
  }, [])
  
  return (
    <React.Fragment>
      <Title>{props.name}</Title>
      <Typography component="p" variant="h4">
        {(props.name === "Movement") ? (props.val ? "Yes" : "No") : Math.round(props.val * 10) / 10}{props.name === "Temperature" && " ºC"}
      </Typography>
      <Typography color="textSecondary" className={classes.depositContext}>
        as of {time}
      </Typography>
      <div>
        <Link color="primary" href="#" onClick={preventDefault}>
          View history
        </Link>
      </div>
    </React.Fragment>
  );
}
