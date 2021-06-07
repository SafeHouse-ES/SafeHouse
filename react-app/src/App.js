import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { BrowserRouter as Router,
    Route,
    Switch,
  } from "react-router-dom";
import Navbar from './Navbar';
import Dashboard from './Dashboard';  
import FormTable from './FormTable';
import SensorSettings from './SensorSettings';

const useStyles = makeStyles((theme) => ({
    root: {
      display: 'flex',
      minHeight: 950
    }
}));

function App() {
    const classes = useStyles();
    return (
        <Router>
            <div className={classes.root}>
                <Navbar />
                <Switch>
                    <Route exact path="/" component={Dashboard} />
                    <Route path="/historic" component={FormTable} />
                    <Route path="/settings" component={SensorSettings} />
                </Switch>
            </div>
        </Router>
    )
}

export default App;