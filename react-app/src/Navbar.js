import React from 'react';
import clsx from 'clsx';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import NotificationsIcon from '@material-ui/icons/Notifications';
import Popover from '@material-ui/core/Popover';
import Paper from '@material-ui/core/Paper';
import { mainListItems, secondaryListItems } from './listItems';
import http from './http-api';

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    paddingRight: 24, // keep right padding when drawer closed
  },
  toolbarIcon: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: '0 8px',
    ...theme.mixins.toolbar,
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 36,
  },
  menuButtonHidden: {
    display: 'none',
  },
  title: {
    flexGrow: 1,
  },
  drawerPaper: {
    position: 'relative',
    whiteSpace: 'nowrap',
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerPaperClose: {
    overflowX: 'hidden',
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    width: theme.spacing(7),
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9),
    },
  },
  appBarSpacer: theme.mixins.toolbar,
  content: {
    flexGrow: 1,
    height: '100vh',
    overflow: 'auto',
  },
  container: {
    paddingTop: theme.spacing(4),
    paddingBottom: theme.spacing(4),
  },
  paper: {
    padding: 5,
    display: 'flex',
    overflow: 'auto',
    flexDirection: 'column',
  },
  fixedHeight: {
    height: 200,
  },
  notifs: {
    padding: 3,
  },
}));

export default function Navbar() {
    const classes = useStyles();
    const [open, setOpen] = React.useState(true);
    const [anchorEl, setAnchorEl] = React.useState(null);
    const [notifs, setNotifs] = React.useState('');
    const [timeTest, setTimeTest] = React.useState('');
    const [time, setTime] = React.useState('');

    const timeNowSecs = Math.round(Date.parse(time) / 1000);
    var timeDiff = [];

    React.useEffect(() => {
      setTime(new Date().toISOString())
      
      const interval=setInterval(()=>{
        setTime(new Date().toISOString())
      },5000)
  
      return()=>clearInterval(interval)
    }, [])

    // React.useEffect(() => {
    //   setTimeTest(new Date().toISOString())
      
    //   const interval=setInterval(()=>{
    //     setTimeTest(new Date().toISOString())
    //   },60000)
  
    //   return()=>clearInterval(interval)
    // }, [])

    // const notifTest = [ {"timestamp": timeNowSecs - 27, "alert_state": "Notificão 1", "description": "Valor temperatura acima"},
    //                     {"timestamp": timeNowSecs - 61, "alert_state": "Notificão 2", "description": "Valor temperatura acima"},
    //                     {"timestamp": timeNowSecs - 126, "alert_state": "Notificão 3", "description": "Valor temperatura acima"},
    //                     {"timestamp": timeNowSecs - 3601, "alert_state": "Notificão 4", "description": "Valor temperatura acima"},
    //                     {"timestamp": timeNowSecs - 7210, "alert_state": "Notificão 5", "description": "Valor temperatura acima"}
    //                   ]

    const getNotifs = async () => {
      http.get('/alerts?seg=60000')
          .then((response) => {
            setNotifs(response.data);
          })
          .catch(error => console.log("Error: ${error}"));
    }

    React.useEffect(() => {
      getNotifs();
      
      const interval=setInterval(()=>{
        getNotifs();
      },10000)
  
      return()=>clearInterval(interval)
    }, [])

    for(let i = 0; i < notifs.length; i++) {
      let diff = timeNowSecs - notifs[i].timestamp;
      if(diff < 60) {
        let val = diff;
        let metric;
        if(diff == 1) {
          metric = "second";  
        } else {
          metric = "seconds";
        }
        timeDiff.push({val: val, metric: metric})
      } else if(diff < 3600) {
        let minutes = Math.round(diff / 60);
        let val = minutes;
        let metric;
        if(minutes == 1) {
          metric = "minute";
        } else {
          metric = "minutes";
        }
        timeDiff.push({val: val, metric: metric})
      } else {
        let hours = Math.floor(diff / 3600);
        let val = hours;
        let metric;
        if(hours == 1) {
          metric = "hour";
        } else {
          metric = "hours";
        }
        timeDiff.push({val: val, metric: metric})
      }
      
    }
    
    const handleDrawerOpen = () => {
        setOpen(true);
    };
    const handleDrawerClose = () => {
        setOpen(false);
    };

    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
      setAnchorEl(null);
    };

    const openNotif = Boolean(anchorEl);
    const id = openNotif ? 'simple-popover' : undefined;
    
    return(
        <div className={classes.root}>
            <CssBaseline />
            <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
                <Toolbar className={classes.toolbar}>
                <IconButton
                    edge="start"
                    color="inherit"
                    aria-label="open drawer"
                    onClick={handleDrawerOpen}
                    className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
                >
                    <MenuIcon />
                </IconButton>
                <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
                    SafeHouse Dashboard
                </Typography>
                <div>
                  <IconButton color="inherit" aria-describedby={id} variant="contained" onClick={handleClick}>
                      <Badge badgeContent={notifs ? notifs.length : 0} color="secondary">
                      <NotificationsIcon/>
                      </Badge>
                  </IconButton>
                  
                        {notifs && (
                          <Popover
                            id={id}
                            open={openNotif}
                            anchorEl={anchorEl}
                            onClose={handleClose}
                            anchorOrigin={{
                              vertical: 'bottom',
                              horizontal: 'right',
                            }}
                            transformOrigin={{
                              vertical: 'top',
                              horizontal: 'right',
                            }}
                          >  
                            <Paper className={classes.paper}>
                              {notifs.map((notif, ind) => (
                                <div>
                                  <Typography className={classes.notifs} component="p">{notif.description} - {notif.alert_state}</Typography>
                                  <Typography color="textSecondary" className={classes.depositContext}>
                                    {timeDiff[ind].val +" "+ timeDiff[ind].metric} ago
                                  </Typography>
                                  <Divider />
                                </div>
                              ))}
                            </Paper>
                          </Popover>  
                        )}
                </div>
                </Toolbar>
            </AppBar>
            <Drawer
                variant="permanent"
                classes={{
                paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
                }}
                open={open}
            >
                <div className={classes.toolbarIcon}>
                <IconButton onClick={handleDrawerClose}>
                    <ChevronLeftIcon />
                </IconButton>
                </div>
                <Divider />
                <List>{mainListItems}</List>
                <Divider />
                {/* <List>{secondaryListItems}</List> */}
            </Drawer>
        </div>
    )
}