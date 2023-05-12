import { Accordion, AccordionDetails, AccordionSummary, Box, Button, Checkbox, FormControl, InputLabel, MenuItem, ListItemText, ListItemIcon, Paper, Rating,Select, Slider ,Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TableSortLabel, TextField, Typography } from "@mui/material";
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
import { red } from "@mui/material/colors";

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
  const [orderBy, setOrderBy] = React.useState('ovr');
  const [selected, setSelected] = React.useState([]);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const [rows, setRows] = React.useState([]);

  const [allItemCount, setAllItemCount] = React.useState(0);
  /*검색 조건들 */
  const [name, setName] = React.useState("");
  const [paySide, setPaySide] = React.useState([0,40]);
  const [ovr, setOvr] = React.useState([0,150]);
  const [season, setSeason] = React.useState(0);
  const [height, setHeight] = React.useState([0,250]);
  
  const [mainPosition, setMainPosition] = React.useState([]);
  const [skill, setSkill] = React.useState(0);
  const [lFoot, setLFoot] = React.useState(0);
  const [rFoot, setRFoot] = React.useState(0);
  const [traits, setTraits] = React.useState(["특성1","특성2","특성3"]);
 
  const [detailStat1, setDetailStat1] = React.useState([]);
  const [detailStat2, setDetailStat2] = React.useState([]);
  const [detailStat3, setDetailStat3] = React.useState([]);
  const [detailStat4, setDetailStat4] = React.useState([]);
  const [detailStat5, setDetailStat5] = React.useState([]);
  const [detailStat6, setDetailStat6] = React.useState([]);
  const [detailStat7, setDetailStat7] = React.useState([]);
  const [detailStat8, setDetailStat8] = React.useState([]);
  const [detailStat9, setDetailStat9] = React.useState([]);
  const [detailStat10, setDetailStat10] = React.useState([]);
  const [detailStat11, setDetailStat11] = React.useState([]);
  const [detailStat12, setDetailStat12] = React.useState([]);
  const [detailStat13, setDetailStat13] = React.useState([]);
  const [detailStat14, setDetailStat14] = React.useState([]);
  const [detailStat15, setDetailStat15] = React.useState([]);
  const [detailStat16, setDetailStat16] = React.useState([]);
  const [detailStat17, setDetailStat17] = React.useState([]);
  const [detailStat18, setDetailStat18] = React.useState([]);
  const [detailStat19, setDetailStat19] = React.useState([]);
  const [detailStat20, setDetailStat20] = React.useState([]);
  const [detailStat21, setDetailStat21] = React.useState([]);
  const [detailStat22, setDetailStat22] = React.useState([]);
  const [detailStat23, setDetailStat23] = React.useState([]);
  const [detailStat24, setDetailStat24] = React.useState([]);
  const [detailStat25, setDetailStat25] = React.useState([]);
  const [detailStat26, setDetailStat26] = React.useState([]);
  const [detailStat27, setDetailStat27] = React.useState([]);
  const [detailStat28, setDetailStat28] = React.useState([]);
  const [detailStat29, setDetailStat29] = React.useState([]);
  const [detailStat30, setDetailStat30] = React.useState([]);
  const [detailStat31, setDetailStat31] = React.useState([]);
  const [detailStat32, setDetailStat32] = React.useState([]);
  const [detailStat33, setDetailStat33] = React.useState([]);
  const [detailStat34, setDetailStat34] = React.useState([]);
  const [teamcolor, setTeamcolor] = React.useState([]);

  const [mainPositionList,setMainPositionList] = React.useState(["ST","LW","CF","RW","CAM","LM","CM","RM","CDM","LWB","CB","RWB","LB","SW","RB","GK"]);
  const [seasonList,setSeasonList] = React.useState([]);
  const [traitsList,setTraitsList] = React.useState([]);
  const [teamcolorList,setTeamcolorList] = React.useState([""]);
  const req = useReq();
  const nav = useNavigate();

  const getData = () => {
   
    req.post({
      url: 'http://localhost:9000/getTeamcolor',
      params: {
        key: "",
        type: "",
        name: "",
        distinct: true
      },
      success: function(data) {
          setTeamcolorList(data);
      }
    });

    req.post({
      url: 'http://localhost:9000/getTrait',
      success: function(data) {
        setTraitsList(data);
      }
    });

    req.post({
      url: 'http://localhost:9000/getSeason',
      success: function(data) {
        setSeasonList(data);
      }
    });
  }

  React.useEffect(()=> {
    // 이곳이 document.ready() 부분~~
    getData();

  },[page, order, orderBy, rowsPerPage]);
  const testData = () => {
    let params = {
      spid: "",
      name: name,
      season: "",
      paySide: paySide,
      ovr: ovr,
      mainPosition: mainPosition,
      birth: "",
      height: height,
      skill: skill,
      nation: "",
      traits: traits,
      detailStat1: detailStat1,
      detailStat2: detailStat2,
      detailStat3: detailStat3,
      detailStat4: detailStat4,
      detailStat5: detailStat5,
      detailStat6: detailStat6,
      detailStat7: detailStat7,
      detailStat8: detailStat8,
      detailStat9: detailStat9,
      detailStat10: detailStat10,
      detailStat11: detailStat11,
      detailStat12: detailStat12,
      detailStat13: detailStat13,
      detailStat14: detailStat14,
      detailStat15: detailStat15,
      detailStat16: detailStat16,
      detailStat17: detailStat17,
      detailStat18: detailStat18,
      detailStat19: detailStat19,
      detailStat20: detailStat20,
      detailStat21: detailStat21,
      detailStat22: detailStat22,
      detailStat23: detailStat23,
      detailStat24: detailStat24,
      detailStat25: detailStat25,
      detailStat26: detailStat26,
      detailStat27: detailStat27,
      detailStat28: detailStat28,
      detailStat29: detailStat29,
      detailStat30: detailStat30,
      detailStat31: detailStat31,
      detailStat32: detailStat32,
      detailStat33: detailStat33,
      detailStat34: detailStat34,          
      clubName: [""],
      teamcolor: teamcolor,
      lfoot: 0,
      rfoot: 0
      
    };
    console.log(params);
  }
  const searchData = () => {

    req.post({
      url: 'http://localhost:9000/getPlayer',
      params: {
        spid: "",
        name: name,
        season: "",
        paySide: paySide,
        ovr: ovr,
        mainPosition: mainPosition,
        birth: "",
        height: height,
        skill: skill,
        nation: "",
        traits: traits,
        detailStat1: detailStat1,
        detailStat2: detailStat2,
        detailStat3: detailStat3,
        detailStat4: detailStat4,
        detailStat5: detailStat5,
        detailStat6: detailStat6,
        detailStat7: detailStat7,
        detailStat8: detailStat8,
        detailStat9: detailStat9,
        detailStat10: detailStat10,
        detailStat11: detailStat11,
        detailStat12: detailStat12,
        detailStat13: detailStat13,
        detailStat14: detailStat14,
        detailStat15: detailStat15,
        detailStat16: detailStat16,
        detailStat17: detailStat17,
        detailStat18: detailStat18,
        detailStat19: detailStat19,
        detailStat20: detailStat20,
        detailStat21: detailStat21,
        detailStat22: detailStat22,
        detailStat23: detailStat23,
        detailStat24: detailStat24,
        detailStat25: detailStat25,
        detailStat26: detailStat26,
        detailStat27: detailStat27,
        detailStat28: detailStat28,
        detailStat29: detailStat29,
        detailStat30: detailStat30,
        detailStat31: detailStat31,
        detailStat32: detailStat32,
        detailStat33: detailStat33,
        detailStat34: detailStat34,          
        clubName: [""],
        teamcolor: teamcolor,
        lfoot: 0,
        rfoot: 0
        
      },
      success: function(data) {
        console.log(data);
        
       
        // setRows(data.list);
        // setAllItemCount(data.allItemCount);
      }
    });
  }


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

  return (
    <Box sx={{ width: '100%' }}>
      <Paper sx={{ width: '100%', mb: 2 }}>
        <Box>
          {/* 이름 */}
          <TextField sx={{ m: 1, minWidth: "30%" }} 
            id="standard-basic" 
            label="선수명" 
            variant="standard" 
            value={name}
            onChange={(e)=>{setName(e.target.value)}}
          />
          <Button sx={{ m: 2}} variant="contained" onClick={()=>{testData()}}>검색</Button>
          <br/>
        </Box>
        <TableContainer>
          <Accordion>
            <AccordionSummary
              expandIcon={<FilterList />}
              aria-controls="panel1a-content"
              id="panel1a-header"
            >
              <Typography>상세 검색</Typography>
            </AccordionSummary>
            <AccordionDetails>
              <Grid container spacing={0} sx={{  display: 'flex'}}>
              {/* ovr */}
              <Box  sx={{ width: 200 ,boxShadow: 2, pr: 3, pl: 1}}>
              <Typography id="input-slider" gutterBottom>
                OVR
              </Typography>
              <Grid container spacing={3}>
              <Grid item xs sx={{ minWidth: "25%" }}  >
              <Input
                value={ovr[0]}
                size="small"
                onChange={(e)=>{
                  let cplist = [...ovr]
                  cplist[0] = e.target.value === ''? '': Number(e.target.value) 
                  setOvr(cplist);
                }}
                inputProps={{
                  min: 0,
                  max: 150,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              <Grid item xs sx={{ minWidth: "50%" }} >
                <Slider
                  min={0}
                  max={150}
                  value={ovr}
                  onChange={(event, newValue) => {
                    setOvr(newValue);
                  }}
                  valueLabelDisplay="auto"
                />
                </Grid>
                <Grid item xs sx={{ minWidth: "25%" }}>
                <Input
                value={ovr[1]}
                size="small"
                onChange={(e)=>{
                  let cplist = [...ovr]
                  cplist[1] = e.target.value === ''? '': Number(e.target.value) 
                  setOvr(cplist);
                }}
                inputProps={{
                  min: 0,
                  max: 150,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              </Grid>
              </Box>


              {/* 급여 */}
              <Box sx={{ width: 200, ml:3,boxShadow: 2, pr: 3, pl: 1 }}>
              <Typography id="input-slider" gutterBottom>
                급여
              </Typography>
              <Grid container spacing={3}>
              <Grid item xs sx={{ minWidth: "25%" }}  >
              <Input
                value={paySide[0]}
                size="small"
                onChange={(e)=>{
                  let cplist = [...paySide]
                  cplist[0] = e.target.value === ''? '': Number(e.target.value) 
                  setPaySide(cplist);
                }}
                inputProps={{
                  min: 0,
                  max: 40,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              <Grid item xs sx={{ minWidth: "50%" }} >
                <Slider
                  min={0}
                  max={40}
                  value={paySide}
                  onChange={(event, newValue) => {
                    setPaySide(newValue);
                  }}
                  valueLabelDisplay="auto"

                />
                </Grid>
                <Grid item xs sx={{ minWidth: "25%" }}>
                <Input
                value={paySide[1]}
                size="small"
                onChange={(e)=>{
                  let cplist = [...paySide]
                  cplist[1] = e.target.value === ''? '': Number(e.target.value) 
                  setPaySide(cplist);
                }}
                inputProps={{
                  min: 0,
                  max: 40,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              </Grid>
              </Box>

              {/* 시즌 */}
              <Box sx={{ width: 350, ml: 3 ,boxShadow: 2, pr: 5}}>

              <Autocomplete
                id="season"
                sx={{ width: 300 }}
                options={seasonList}
                autoHighlight
                isOptionEqualToValue={(option,value) => option.seasonId === value.seasonId}
                getOptionLabel={(option) => option.className}
                onChange={(props, option, { selected }) => {
                  console(option);
                  setSeason(option.seasonId);
                }}
                renderOption={(props, option) => (
                  <Box component="li" sx={{ '& > img': { mr: 2, flexShrink: 0 } }} {...props}>
                    <img
                      loading="lazy"
                      width="20"
                      src={option.seasonImg}
                      alt="Season"
                    />
                    {option.className}
                  </Box>
                )}
                renderInput={(params) => (

                  <TextField
                    {...params}
                    label="시즌"
                    inputProps={{
                      ...params.inputProps,
                      autoComplete: 'new-password', // disable autocomplete and autofill
                    }}
                  />
                )}
              />
              </Box>

              {/* 왼발 */}
              <Box sx={{ width: 80, ml: 2,boxShadow: 2}}>
              <FormControl variant="standard" sx={{ m: 1, minWidth: 50 }} >
              <InputLabel id="demo-simple-select-filled-label">왼발</InputLabel>
                <Select
                  labelId="demo-simple-select-filled-label"
                  id="lFoot"
                  value={lFoot}
                  onChange={(e)=>{setLFoot(e.target.value)}}
                >
                  <MenuItem value="0">
                    <em>전체</em>
                  </MenuItem>
                  {
                    [...Array(parseInt(5))].map((n, i) => {
                      return ( <MenuItem value={++i}>{i}</MenuItem> )
                    })
                  }

                </Select>
              </FormControl>
              </Box>

              {/* 오른발 */}
            <Box sx={{ width: 80, ml: 2,boxShadow: 2}}>
              <FormControl variant="standard" sx={{ m: 1, minWidth: 50 }} >
              <InputLabel id="demo-simple-select-filled-label">오른발</InputLabel>
                <Select
                  labelId="demo-simple-select-filled-label"
                  id="rFoot"
                  value={rFoot}
                  onChange={(e)=>{setRFoot(e.target.value)}}
                >
                  <MenuItem value="0">
                    <em>전체</em>
                  </MenuItem>
                  {
                    [...Array(parseInt(5))].map((n, i) => {
                      return ( <MenuItem value={++i}>{i}</MenuItem> )
                    })
                  }

                </Select>
              </FormControl>
              </Box>

              </Grid>

              <Grid container spacing={0} sx={{  display: 'flex', mt:2}}>
              {/* 포지션 */}
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
                sx={{ width: 300 ,boxShadow: 2, pr: 1, pl: 1}}
              />

              {/* 키 */}
              <Box sx={{ width: 200, ml:3, boxShadow: 2, pr: 3, pl: 1, }}>
              <Typography id="input-slider" gutterBottom>
                키
              </Typography>
              <Grid container spacing={3}>
              <Grid item xs sx={{ minWidth: "25%" }}  >
              <Input
                value={height[0]}
                size="small"
                onChange={(e)=>{
                  let cplist = [...height]
                  cplist[0] = e.target.value === ''? '': Number(e.target.value) 
                  setHeight(cplist);
                }}
                inputProps={{
                  min: 0,
                  max: 250,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              <Grid item xs sx={{ minWidth: "50%" }} >
                <Slider
                  min={0}
                  max={250}
                  value={height}
                  onChange={(event, newValue) => {
                    setHeight(newValue);
                  }}
                  valueLabelDisplay="auto"

                />
                </Grid>
                <Grid item xs sx={{ minWidth: "25%" }}>
                <Input
                value={height[1]}
                size="small"
                onChange={(e)=>{
                  let cplist = [...height]
                  cplist[1] = e.target.value === ''? '': Number(e.target.value) 
                  setHeight(cplist);
                }}
                inputProps={{
                  min: 0,
                  max: 250,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              </Grid>
              </Box>

               {/* 개인기 */}          
               <Box sx={{ '& > legend': { mt: 2 }, ml:3, boxShadow: 2}}>
                <Typography component="legend">개인기</Typography>
                <Rating
                  name="simple-controlled"
                  max={6}
                  value={skill}
                  onChange={(event, newValue) => {
                    setSkill(newValue);
                  }}
                />
            </Box>

           

              {/*특성*/}
            <Autocomplete
              multiple
              id="traits"
              options={traitsList}
              disableCloseOnSelect
              isOptionEqualToValue={(option,value) => option.name === value.name}
              getOptionLabel={(option) => option.name}
              onChange={(props, option, { selected }) => {
                setTraits(option);
              }}
              renderOption={(props, option, { selected }) => (
                
                <li {...props} >
                  <Checkbox
                    icon={icon}
                    checkedIcon={checkedIcon}
                    style={{ marginRight: 8 }}
                    checked={selected}
                  />
                  {option.name}
                </li>
              )}
              renderInput={(params) => (
                <TextField {...params} label="특성" placeholder="특성"   />
                
              )}
              sx={{ width: 300, ml:2, boxShadow: 2, pr: 1, pl: 1,}}
            />
            
            </Grid>
            <Grid container spacing={0} sx={{  display: 'flex', mt:2}}>
              {/* 팀컬러 */}
              <Autocomplete
                multiple
                id="teamcolor"
                options={teamcolorList}
                disableCloseOnSelect
                isOptionEqualToValue={(option,value) => option.key === value.key}
                getOptionLabel={(option) => option.name}

                onChange={(props, option, { selected }) => {
                  let cplist = new Array();
                  option.map((n)=>{
                    cplist.push(n.key);
                  })
                  setTeamcolor(cplist);
                }}
                renderOption={(props, option, { selected }) => (
                  
                  <li {...props} >
                    <Checkbox
                      icon={icon}
                      checkedIcon={checkedIcon}
                      style={{ marginRight: 8 }}
                      checked={selected}
                    />
                    {option.name}
                  </li>
                )}
                renderInput={(params) => (
                  
                  <TextField {...params}  label="팀컬러" placeholder="팀컬러"   />
                  
                )}
                sx={{ width: 300 ,boxShadow: 2, pr: 1, pl: 1}}
              />
              </Grid>

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


