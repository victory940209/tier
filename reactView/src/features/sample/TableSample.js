import { Accordion, AccordionDetails, AccordionSummary, Box, Button, Checkbox, FormControl, InputLabel, MenuItem, Paper, Select, Slider ,Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TableSortLabel, TextField, Typography } from "@mui/material";
import { visuallyHidden } from "@mui/utils";
import * as React from 'react';
import { useNavigate } from 'react-router';
import useReq from '../../app/request';
import { FilterList } from "@mui/icons-material"
import Autocomplete from '@mui/material/Autocomplete';
import CheckBoxOutlineBlankIcon from '@mui/icons-material/CheckBoxOutlineBlank';
import CheckBoxIcon from '@mui/icons-material/CheckBox';
import MuiInput from '@mui/material/Input';
import { styled } from '@mui/material/styles';
import Grid from '@mui/material/Grid';

const Input = styled(MuiInput)`
  width: 42px;
`;
const icon = <CheckBoxOutlineBlankIcon fontSize="small" />;
const checkedIcon = <CheckBoxIcon fontSize="small" />;
/**
 *  참고 URL :
 *  https://stackblitz.com/edit/react-rfpsqp?file=demo.js
 */
const headCells = [
  {
    id: 'seq',
    align: 'center',
    disablePadding: true,
    label: '순번',
  },
  {
    id: 'name' + 'seq',
    align: 'center',
    disablePadding: false,
    label: '고객명',
  },
  {
    id: 'loanSeqNo',
    align: 'center',
    disablePadding: false,
    label: '대출번호',
  },
  {
    id: 'contStat',
    align: 'center',
    disablePadding: false,
    label: '계약상태',
  },
];

function EnhancedTableHead(props) {
  const {
    onSelectAllClick,
    order,
    orderBy,
    numSelected,
    rowCount,
    onRequestSort,
  } = props;
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow>
        <TableCell padding="checkbox">
          <Checkbox
            color="primary"
            indeterminate={numSelected > 0 && numSelected < rowCount}
            checked={rowCount > 0 && numSelected === rowCount}
            onChange={onSelectAllClick}
            inputProps={{
              "aria-label": "전체선택",
            }}
          />
        </TableCell>
        {headCells.map((headCell, idx) => (
          <TableCell
            key={headCell.id + idx}
            align={headCell.align}
            padding={headCell.disablePadding ? "none" : "normal"}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            <TableSortLabel
              active={orderBy === headCell.id}
              direction={orderBy === headCell.id ? order : "asc"}
              onClick={createSortHandler(headCell.id)}
            >
              {headCell.label}
              {orderBy === headCell.id ? (
                <Box component="span" sx={visuallyHidden}>
                  {order === "desc" ? "sorted descending" : "sorted ascending"}
                </Box>
              ) : null}
            </TableSortLabel>
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
}

export default function TableSample() {
  const [order, setOrder] = React.useState('asc');
  const [orderBy, setOrderBy] = React.useState('calories');
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [rows, setRows] = React.useState([]);
  const [allItemCount, setAllItemCount] = React.useState(0);
  const [name, setName] = React.useState("");

  const [PaySide, setPaySide] = React.useState([0,40]);
  const [maxPaySide, setMaxPaySide] = React.useState(0);
  const [minPaySide, setMinPaySide] = React.useState(0);
  const [ovr, setOvr] = React.useState(0);
  const [season, setSeason] = React.useState(0);
  const mainPositionList = ["ST","LW","CF","RW","CAM","LM","CM","RM","CDM","LWB","CB","RWB","LB","SW","RB","GK"];
  const [mainPosition, setMainPosition] = React.useState([]);
  const req = useReq();
  const nav = useNavigate();

  const getData = () => {
    let params = {
      page : page,
      order : order,
      orderBy : orderBy,
      rowsPerPage : rowsPerPage,
      name: name,
      maxPaySide: PaySide[0],
      minPaySide : PaySide[1],
      ovr : ovr,
      season : season,
      mainPosition : mainPosition
      
    };
    console.log(params);
    req.post({
      url: '/fifa/user/getSampleTableData',
      params: {
        page : page,
        order : order,
        orderBy : orderBy,
        rowsPerPage : rowsPerPage,
        name: name,
        maxPaySide: maxPaySide,
        minPaySide : minPaySide,
        ovr : ovr,
        season : season,
        mainPosition : mainPosition
        
      },
      success: function(data) {
        setRows(data.list);
        setAllItemCount(data.allItemCount);
      }
    });
  }

  React.useEffect(()=> {
    getData();
  },[page, order, orderBy, rowsPerPage]);

  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === 'asc';
    setOrder(isAsc ? 'desc' : 'asc');
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelected = rows.map((n) => n.seq);
      setSelected(newSelected);
      return;
    }
    setSelected([]);
  };

  const handleClick = (event, seq) => {
    const selectedIndex = selected.indexOf(seq);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, seq);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1),
      );
    }

    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const isSelected = (seq) => selected.indexOf(seq) !== -1;

  const alertSelected = () => {
    alert(selected);
  }

  const handleChange = (event, newValue) => {
    setPaySide(newValue);
  };

  return (
    <Box sx={{ width: '100%' }}>
      <Paper sx={{ width: '100%', mb: 2 }}>
        <Box>
        <TextField sx={{ m: 1, minWidth: "30%" }} 
            id="standard-basic" 
            label="선수명" 
            variant="standard" 
            value={name}
            onChange={(e)=>{setName(e.target.value)}}
          />
          <Button sx={{ m: 2}} variant="contained" onClick={()=>{getData()}}>검색</Button>
          <br/>

          <Box sx={{ width: 320 }}>
          <Typography id="input-slider" gutterBottom>
            급여
          </Typography>
          <Grid container spacing={3}>
          <Grid item xs sx={{ minWidth: "20%" }}  >
          <Input
            value={PaySide[0]}
            size="small"

            inputProps={{
              step: 0,
              min: 0,
              max: 40,
              type: 'number',
              'aria-labelledby': 'input-slider',
            }}
          />
          </Grid>
          <Grid item xs sx={{ minWidth: "60%" }} >
            <Slider
              min={0}
              max={40}
              value={PaySide}
              onChange={handleChange}
              valueLabelDisplay="auto"

            />
            </Grid>
            <Grid item xs sx={{ minWidth: "20%" }}>
             <Input
            value={PaySide[1]}
            size="small"
           
            inputProps={{
              step: 40,
              min: 0,
              max: 40,
              type: 'number',
              'aria-labelledby': 'input-slider',
            }}
          />
          </Grid>
          </Grid>
          </Box>

          <FormControl variant="standard" sx={{ m: 1, minWidth: 100 }}>
          <InputLabel id="demo-simple-select-filled-label">시즌</InputLabel>
            <Select
              labelId="demo-simple-select-filled-label"
              id="season"
              value={season}
              onChange={(e)=>{setSeason(e.target.value)}}
            >
              <MenuItem value="0">
                <em>전체</em>
              </MenuItem>
              {
                // Season  검색해서 가져옴
                [...Array(parseInt(20))].map((n, i) => {
                  return ( <MenuItem value={++i}>{i}</MenuItem> )
                })
              }

            </Select>
          </FormControl>

          <Autocomplete
            multiple
            id="mainPositionList"
            options={mainPositionList}
            disableCloseOnSelect
            getOptionLabel={(option) => option}
            onChange={(props, option, { selected }) => {
              setMainPosition(option);
            }}
            renderOption={(props, option, { selected }) => (
               
              <li {...props} >
                <Checkbox
                  icon={icon}
                  checkedIcon={checkedIcon}
                  style={{ marginRight: 8 }}
                  checked={selected}
                />
                {option}
              </li>
            )}
            renderInput={(params) => (
              <TextField {...params} label="포지션" placeholder="포지션"   />
              
            )}
            sx={{ width: 300 }}
          />





          <Typography sx={{ m: 2, color:"red"}}>
            ※ 실제 검색이 되진 않습니다. 데이터 전달부만 확인해주세요.
          </Typography>
        </Box>
        <TableContainer>
          <Accordion>
            <AccordionSummary
              expandIcon={<FilterList />}
              aria-controls="panel1a-content"
              id="panel1a-header"
            >
              <Typography></Typography>
            </AccordionSummary>
            <AccordionDetails>
              <Typography>
                필터 영역입니다.
              </Typography>
            </AccordionDetails>
          </Accordion>
          <Table
            sx={{ minWidth: 750 }}
            aria-labelledby="tableTitle"
            size={'medium'}
          >
            <EnhancedTableHead
              numSelected={selected.length}
              order={order}
              orderBy={orderBy}
              onSelectAllClick={handleSelectAllClick}
              onRequestSort={handleRequestSort}
              rowCount={rows.length}
            />
            <TableBody>
              {rows
                .map((row, index) => {
                  const isItemSelected = isSelected(row.seq);
                  const labelId = `enhanced-table-checkbox-${index}`;

                  return (
                    <TableRow
                      hover
                      onClick={(event) => handleClick(event, row.seq)}
                      role="checkbox"
                      aria-checked={isItemSelected}
                      tabIndex={-1}
                      key={index}
                      selected={isItemSelected}
                      sx={{ cursor: 'pointer' }}
                    >
                      <TableCell padding="checkbox">
                        <Checkbox
                          color="primary"
                          checked={isItemSelected}
                          inputProps={{
                            'aria-labelledby': labelId,
                          }}
                        />
                      </TableCell>
                      <TableCell
                        component="th"
                        id={labelId}
                        scope="row"
                        padding="none"
                        align="center"
                      >
                        {row.seq}
                      </TableCell>
                      <TableCell align="center">{row.name}</TableCell>
                      <TableCell align="center">{row.loanSeqNo}</TableCell>
                      <TableCell align="center">{row.contStat}</TableCell>
                    </TableRow>
                  );
                })}
            </TableBody>
          </Table>
        </TableContainer>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={allItemCount}
          rowsPerPage={rowsPerPage}
          page={page}
          onPageChange={handleChangePage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </Paper>
      <Box dir="rtl">
        <Button variant="contained" onClick={()=>{alertSelected()}}>선택값 alert</Button>
      </Box>
      <Button variant="contained" color="secondary" onClick={()=>{nav("/sample")}}>Sample 홈으로..</Button>
    </Box>
  );
}


