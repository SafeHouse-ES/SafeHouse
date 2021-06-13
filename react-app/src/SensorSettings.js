import React from 'react';
import http from './http-api.js';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import Title from './Title.js';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import axios from 'axios';

const useStyles = makeStyles((theme) => ({
    paper: {
        padding: theme.spacing(2),
        display: 'flex',
        overflow: 'auto',
        flexDirection: 'column',
    },
    pad: {
      paddingTop: 100,
      paddingLeft: 50
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
      },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
    sensorForm: {
        display: "flex",
        justifyContent: "center",
        alignItems: "center"
    },
    formItem: {
        padding: 10
    }
  }));


export default function SensorSettings() {
    const classes = useStyles();
    const [room, setRoom] = React.useState('');
    const [metric, setMetric] = React.useState('');
    const [sVal, setSVal] = React.useState('');
    const [order, setOrder] = React.useState('');
    const [device, setDevice] = React.useState('');
    const [dVal, setDVal] = React.useState('');
    const [settings, setSettings] = React.useState('');

    const getSettings = async () => {
        axios.get('http://192.168.160.87:31005/all')
            .then((response) => {
              setSettings(response.data);
            })
            .catch(error => console.log("Error: ${error}"));
    }

    React.useEffect(() => {
        getSettings();
    }, []);
    
    const handleChangeRoom = (event) => {
        setRoom(event.target.value);
    };
    const handleChangeMetric = (event) => {
        setMetric(event.target.value);
    };
    const handleChangeOrder = (event) => {
        setOrder(event.target.value);
    };

    const postSetting = async () => {

        axios.post(`http://192.168.160.87:31005/add?room=${room}&metric=${metric}&sVal=${sVal}&order=${order}&device=${device}&dVal=${dVal}`)
            .then(response => console.log(response.data.id));
            
      }

    const handleSubmit = (e) => {
        e.preventDefault();
        postSetting();
    }
   
    return(
        <div>
            <div className={classes.pad}>
            <Grid item>
                <Paper className={classes.paper}>
                <React.Fragment>
                    <Title>All sensor settings</Title>
                    <Table size="small">
                    <TableHead>
                        <TableRow>
                        <TableCell>Room</TableCell>
                        <TableCell>Metric</TableCell>
                        <TableCell>Value Limit</TableCell>
                        <TableCell>Higher/Lower</TableCell>
                        <TableCell>Device</TableCell>
                        <TableCell align="right">Command</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {settings && settings.map((row) => (
                        <TableRow key={row.id}>
                            <TableCell>{row.roomID}</TableCell>
                            <TableCell>{row.metric}</TableCell>
                            <TableCell>{row.sensorValue}</TableCell>
                            <TableCell>{row.order ? "Higher" : "Lower"}</TableCell>
                            <TableCell>{row.deviceID}</TableCell>
                            <TableCell align="right">{row.deviceValue ? 'Activate' : 'Deactivate'}</TableCell>
                        </TableRow>
                        ))}
                    </TableBody>
                    </Table>
                </React.Fragment>
                </Paper>
            </Grid>
            </div>
            <div className={classes.pad}>
                <Grid item>
                    <Paper className={classes.paper}>
                        <Title>Add sensor setting</Title>
                            <form className={classes.sensorForm} noValidate autoComplete="off" onSubmit={handleSubmit}>
                                <div className={classes.formItem}>
                                    <FormControl className={classes.formControl}>
                                        <InputLabel id="room">Room</InputLabel>
                                        <Select
                                        labelId="demo-simple-select-label"
                                        id="demo-simple-select"
                                        value={room}
                                        onChange={handleChangeRoom}
                                        >
                                            <MenuItem value={"Kitchen"}>Kitchen</MenuItem>
                                            <MenuItem value={"LivingRoom"}>Living Room</MenuItem>
                                            <MenuItem value={"Room1"}>Room 1</MenuItem>
                                            <MenuItem value={"Room2"}>Room 2</MenuItem>
                                        </Select>
                                    </FormControl>
                                </div>
                                <div className={classes.formItem}>    
                                    <FormControl className={classes.formControl}>
                                        <InputLabel id="metric">Metric</InputLabel>
                                        <Select
                                        labelId="demo-simple-select-label"
                                        id="demo-simple-select"
                                        value={metric}
                                        onChange={handleChangeMetric}
                                        >
                                            <MenuItem value={"temperature"}>Temperature</MenuItem>
                                            <MenuItem value={"luminosity"}>Luminescence</MenuItem>
                                        </Select>
                                    </FormControl>
                                </div>
                                <div className={classes.formItem}>
                                    <TextField required id="sVal" label="Value Limit" defaultValue="" onChange={(e) => setSVal(e.target.value)} value={sVal} />
                                </div>
                                <div className={classes.formItem}>
                                    <FormControl className={classes.formControl}>
                                        <InputLabel id="order">Higher/Lower</InputLabel>
                                        <Select
                                        labelId="demo-simple-select-label"
                                        id="demo-simple-select"
                                        value={order}
                                        onChange={handleChangeOrder}
                                        >
                                            <MenuItem value={0}>Higher</MenuItem>
                                            <MenuItem value={1}>Lower</MenuItem>
                                        </Select>
                                    </FormControl>
                                </div>
                                <div className={classes.formItem}>
                                    <TextField required id="device" label="Device" defaultValue="" onChange={(e) => setDevice(e.target.value)} value={device} />
                                </div>
                                <div className={classes.formItem}>
                                    <TextField required id="dVal" label="Command" defaultValue="" onChange={(e) => setDVal(e.target.value)} value={dVal} />
                                </div>
                                <Button type="submit">Submit</Button>
                            </form>                        
                    </Paper>
                </Grid>
            </div>
        </div>
    )
}