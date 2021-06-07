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

// Generate Order Data
function createData(id, date, name, shipTo, paymentMethod, amount) {
  return { id, date, name, shipTo, paymentMethod, amount };
}

const rows = [
  createData(0, '16 Mar, 2019', 'Elvis Presley', 'Tupelo, MS', 'VISA ⠀•••• 3719', 312.44),
  createData(1, '16 Mar, 2019', 'Paul McCartney', 'London, UK', 'VISA ⠀•••• 2574', 866.99),
  createData(2, '16 Mar, 2019', 'Tom Scholz', 'Boston, MA', 'MC ⠀•••• 1253', 100.81),
  createData(3, '16 Mar, 2019', 'Michael Jackson', 'Gary, IN', 'AMEX ⠀•••• 2000', 654.39),
  createData(4, '15 Mar, 2019', 'Bruce Springsteen', 'Long Branch, NJ', 'VISA ⠀•••• 5919', 212.79),
];

function preventDefault(event) {
  event.preventDefault();
}

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

  console.log(historicData);

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
