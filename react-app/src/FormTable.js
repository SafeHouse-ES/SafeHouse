import React from 'react';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Title from './Title';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import http from './http-api.js';


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
  }
}));

export default function FormTable() {
  const classes = useStyles();

  let [historicData, setHistoricData] = React.useState('');
  const dateInf = new Date();
  const dateSup = new Date();
  
  dateInf.setHours(dateInf.getHours()-1);

  const getHistoricData = async () => {
    http.get(`/all/interval?inf=${dateInf.toISOString()}&sup=${dateSup.toISOString()}`)
        .then((response) => {
          setHistoricData(response.data);
        })
        .catch(error => console.log("Error: ${error}"));
  }

  React.useEffect(() => {
    getHistoricData();
  }, []);

  return (
    <div className={classes.pad}>
      <Grid item>
        <Paper className={classes.paper}>
          <React.Fragment>
            <Title>All sensor data from the last hour</Title>
            <Table size="small">
              <TableHead>
                <TableRow>
                  <TableCell>Date</TableCell>
                  <TableCell>Room</TableCell>
                  <TableCell>Temperature (ºC)</TableCell>
                  <TableCell>Luminescence</TableCell>
                  <TableCell align="right">Movement</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {historicData && historicData.map((row) => (
                  <TableRow key={row.timestamp}>
                    <TableCell>{new Date(row.timestamp).toLocaleTimeString()}</TableCell>
                    <TableCell>{row.id}</TableCell>
                    <TableCell>{Math.round(row.temperature * 10) / 10}</TableCell>
                    <TableCell>{Math.round(row.luminosity * 10) / 10}</TableCell>
                    <TableCell align="right">{row.movement ? 'Yes' : 'No'}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </React.Fragment>
        </Paper>
      </Grid>
    </div>
  );
}
