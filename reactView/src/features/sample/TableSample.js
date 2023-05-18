/* eslint-disable */
import { Accordion, AccordionDetails, AccordionSummary, Box, Button, Divider, Checkbox, Card, Chip, FormControl, FormControlLabel, InputLabel, MenuItem, Paper, Rating, Select, Slider, ToggleButtonGroup, Table, TableBody, TableCell, TableContainer, TableHead, TablePagination, TableRow, TableSortLabel, TextField, ToggleButton, Typography } from "@mui/material";
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
import { pink, green, blue, yellow } from "@mui/material/colors";



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
    id: 'id',
    align: 'center',
    disablePadding: true,
    label: '',
  },
  {
    id: 'name',
    align: 'center',
    disablePadding: true,
    label: '이름',
  },
  {
    id: 'paySide',
    align: 'center',
    disablePadding: false,
    label: '급여',
  },
  {
    id: 'ovr',
    align: 'center',
    disablePadding: false,
    label: 'ovr',
  },
  {
    id: 'lfoot',
    align: 'center',
    disablePadding: false,
    label: '왼발',
  },
  {
    id: 'rfoot',
    align: 'center',
    disablePadding: false,
    label: '오른발',
  },
  {
    id: 'pay',
    align: 'center',
    disablePadding: false,
    label: '가격',
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
  const [rowsPerPage, setRowsPerPage] = React.useState(25);
  const [rows, setRows] = React.useState([]);
  const [sAllselected, setsAllselected] = React.useState(false);
  
  const [allItemCount, setAllItemCount] = React.useState(0);
  /*검색 조건들 */
  const [name, setName] = React.useState("");
  const [paySide, setPaySide] = React.useState([0,40]);
  const [ovr, setOvr] = React.useState([0,150]);
  const [season, setSeason] = React.useState([]);
  const [height, setHeight] = React.useState([0,250]);
  
  const [skill, setSkill] = React.useState(0);
  const [lFoot, setLFoot] = React.useState(0);
  const [rFoot, setRFoot] = React.useState(0);
  const [traits, setTraits] = React.useState([""]);
 
  const [detailStat, setDetailStat] = React.useState({
  detailStat1 : [0,200],
  detailStat2 : [0,200],
  detailStat3 : [0,200],
  detailStat4 : [0,200],
  detailStat5 : [0,200],
  detailStat6 : [0,200],
  detailStat7 : [0,200],
  detailStat8 : [0,200],
  detailStat9 : [0,200],
  detailStat10 : [0,200],
  detailStat11 : [0,200],
  detailStat12 : [0,200],
  detailStat13 : [0,200],
  detailStat14 : [0,200],
  detailStat15 : [0,200],
  detailStat16 : [0,200],
  detailStat17 : [0,200],
  detailStat18 : [0,200],
  detailStat19 : [0,200],
  detailStat20 : [0,200],
  detailStat21 : [0,200],
  detailStat22 : [0,200],
  detailStat23 : [0,200],
  detailStat24 : [0,200],
  detailStat25 : [0,200],
  detailStat26 : [0,200],
  detailStat27 : [0,200],
  detailStat28 : [0,200],
  detailStat29 : [0,200],
  detailStat30 : [0,200],
  detailStat31 : [0,200],
  detailStat32 : [0,200],
  detailStat33 : [0,200],
  detailStat34 : [0,200]
  });
  const [teamcolor, setTeamcolor] = React.useState([""]);

  const [detailStat_1, setDetailStat_1] = React.useState();
  const [detailStat_1Nm, setDetailStat_1Nm] = React.useState("");
  const [detailStat_1v, setDetailStat_1v] = React.useState([0,200]);
  const [detailStat_2, setDetailStat_2] = React.useState();
  const [detailStat_2Nm, setDetailStat_2Nm] = React.useState("");
  const [detailStat_2v, setDetailStat_2v] = React.useState([0,200]);
  const [detailStat_3, setDetailStat_3] = React.useState();
  const [detailStat_3Nm, setDetailStat_3Nm] = React.useState("");
  const [detailStat_3v, setDetailStat_3v] = React.useState([0,200]);
  const [fw, setFw] = React.useState(true);
  const [mf, setMf] = React.useState(true);
  const [df, setDf] = React.useState(true);

  const [mainPositionList, setMainPositionList] = React.useState({
    ST: false,
    LW: false,
    CF: false,
    RW: false,
    CAM: false,
    LM: false,
    CM: false,
    RM: false,
    CDM: false,
    LWB: false,
    CB: false,
    RWB: false,
    LB: false,
    RB: false,
    GK: false
  });
  
  const [detailStatNm, setDetailStatNm] = React.useState({
    detailStat1 : "속력",
    detailStat2 : "가속력",
    detailStat3 : "골 결정력",
    detailStat4 : "슛 파워",
    detailStat5 : "중거리 슛",
    detailStat6 : "위치 선정",
    detailStat7 : "발리슛",
    detailStat8 : "페널티 킥",
    detailStat9 : "짧은 패스",
    detailStat10 : "시야",
    detailStat11 : "크로스",
    detailStat12 : "긴 패스",
    detailStat13 : "프리킥",
    detailStat14 : "커브",
    detailStat15 : "드리블",
    detailStat16 : "볼 컨트롤",
    detailStat17 : "민첩성",
    detailStat18 : "밸런스",
    detailStat19 : "반응 속도",
    detailStat20 : "대인 수비",
    detailStat21 : "태클",
    detailStat22 : "가로채기",
    detailStat23 : "헤더",
    detailStat24 : "슬라이딩 태클",
    detailStat25 : "몸싸움",
    detailStat26 : "스태미너",
    detailStat27 : "적극성",
    detailStat28 : "점프",
    detailStat29 : "침착성",
    detailStat30 : "GK 다이빙",
    detailStat31 : "GK 핸들링",
    detailStat32 : "GK 킥",
    detailStat33 : "GK 반응속도",
    detailStat34 : "GK 위치 선정",
  });

  const {ST, LW, CF, RW, CAM, LM, CM, RM, CDM, LWB, CB, RWB, LB, RB, GK} = mainPositionList;
  const PhandleChange = (event) => {
    setMainPositionList({
      ...mainPositionList,
      [event.target.name]: event.target.checked,
    });
    
    if({ST} && {LW} && {RW} && {CF})setFw(true);
    if(!{ST} || !{LW} || !{RW} || !{CF})setFw(false);
    if({CAM} && {LM} && {CM} && {RM}  && {CDM})setMf(true);
    if(!{CAM} || !{LM} || !{CM} || !{RM} || !{CDM})setMf(false);
    if({LWB} && {CB} && {RWB} && {LB}  && {RB})setDf(true);
    if(!{LWB} || !{CB} || !{RWB} || !{LB} || !{RB})setDf(false);
    
  };


  const [tcType, setTcType] = React.useState("");
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
        type: tcType,
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
  const searchTC = (TypeData)=> {

    req.post({
      url: 'http://localhost:9000/getTeamcolor',
      params: {
        key: "",
        type: TypeData,
        name: "",
        distinct: true
      },
      success: function(data) {
          setTeamcolorList(data);
      }
    });
  }
  const SetmainPosition = ()=>{
    let mainPosition = [""];

    Object.entries(mainPositionList).map((data)=>{
      if(data[1]){
        mainPosition.push(String(data[0]));
      }
    })

    return mainPosition;
    
  }

  React.useEffect(()=> {
    // 이곳이 document.ready() 부분~~
    getData();

  },[page, order, orderBy, rowsPerPage]);


  const testData = () => {
    let seasonNm = [""];

    if(season.length == seasonList.length || season == null){
      console.log("all");
    }else{
      season.map((n)=>{
        seasonList.map((m)=>{
          if(n == m.seasonId){
            let seasonNmstr = m.className.replaceAll(" ", "").replaceAll("C–", "");
            seasonNm.push(seasonNmstr.substring(0, seasonNmstr.indexOf("(")));
          }
        })
        
      })
    }
    req.post({
      url: 'http://localhost:9000/getPlayer',
    params : {
      page: page,
      rowsPerPage: rowsPerPage,
      key: "",
      name: name,
      season: seasonNm,
      paySide: paySide,
      ovr: ovr,
      mainPosition: SetmainPosition(),
      birth: "",
      height: height,
      skill: skill,
      nation: "",
      traits: traits,
      detailStat1: detailStat.detailStat1,
      detailStat2: detailStat.detailStat2,
      detailStat3: detailStat.detailStat3,
      detailStat4: detailStat.detailStat4,
      detailStat5: detailStat.detailStat5,
      detailStat6: detailStat.detailStat6,
      detailStat7: detailStat.detailStat7,
      detailStat8: detailStat.detailStat8,
      detailStat9: detailStat.detailStat9,
      detailStat10:detailStat.detailStat10,
      detailStat11:detailStat.detailStat11,
      detailStat12:detailStat.detailStat12,
      detailStat13:detailStat.detailStat13,
      detailStat14:detailStat.detailStat14,
      detailStat15:detailStat.detailStat15,
      detailStat16:detailStat.detailStat16,
      detailStat17:detailStat.detailStat17,
      detailStat18:detailStat.detailStat18,
      detailStat19:detailStat.detailStat19,
      detailStat20:detailStat.detailStat20,
      detailStat21:detailStat.detailStat21,
      detailStat22:detailStat.detailStat22,
      detailStat23:detailStat.detailStat23,
      detailStat24:detailStat.detailStat24,
      detailStat25:detailStat.detailStat25,
      detailStat26:detailStat.detailStat26,
      detailStat27:detailStat.detailStat27,
      detailStat28:detailStat.detailStat28,
      detailStat29:detailStat.detailStat29,
      detailStat30:detailStat.detailStat30,
      detailStat31:detailStat.detailStat31,
      detailStat32:detailStat.detailStat32,
      detailStat33:detailStat.detailStat33,
      detailStat34:detailStat.detailStat34,          
      clubName: [""],
      teamcolor: teamcolor,
      lfoot: lFoot,
      rfoot: rFoot
        },
        success: function(data) {
          console.log(data);
          
        
          setRows(data.list);
          setAllItemCount(data.allItemCount);
        }
      });  

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
        mainPosition: SetmainPosition(),
        birth: "",
        height: height,
        skill: skill,
        nation: "",
        traits: traits,
        detailStat1: detailStat.detailStat1,
        detailStat2: detailStat.detailStat2,
        detailStat3: detailStat.detailStat3,
        detailStat4: detailStat.detailStat4,
        detailStat5: detailStat.detailStat5,
        detailStat6: detailStat.detailStat6,
        detailStat7: detailStat.detailStat7,
        detailStat8: detailStat.detailStat8,
        detailStat9: detailStat.detailStat9,
        detailStat10:detailStat.detailStat10,
        detailStat11:detailStat.detailStat11,
        detailStat12:detailStat.detailStat12,
        detailStat13:detailStat.detailStat13,
        detailStat14:detailStat.detailStat14,
        detailStat15:detailStat.detailStat15,
        detailStat16:detailStat.detailStat16,
        detailStat17:detailStat.detailStat17,
        detailStat18:detailStat.detailStat18,
        detailStat19:detailStat.detailStat19,
        detailStat20:detailStat.detailStat20,
        detailStat21:detailStat.detailStat21,
        detailStat22:detailStat.detailStat22,
        detailStat23:detailStat.detailStat23,
        detailStat24:detailStat.detailStat24,
        detailStat25:detailStat.detailStat25,
        detailStat26:detailStat.detailStat26,
        detailStat27:detailStat.detailStat27,
        detailStat28:detailStat.detailStat28,
        detailStat29:detailStat.detailStat29,
        detailStat30:detailStat.detailStat30,
        detailStat31:detailStat.detailStat31,
        detailStat32:detailStat.detailStat32,
        detailStat33:detailStat.detailStat33,
        detailStat34:detailStat.detailStat34,
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

  const setImage = (e) =>{
    // 액션 이미지
    let aurl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p" + e.row.id +".png";
   
    
    // 시즌 이미지
    let surl = "";
    let tmp = e.row.id;
    tmp = tmp.substring(0,3);
    tmp = parseInt(tmp);
    seasonList.map((data)=>{
      if(data.seasonId == tmp){
        surl = data.seasonImg;
      }
    })
    // 배경 이미지
    let bgurl = "";
    bgurl = "./season/" + tmp + ".png";
    

    return(
      <div>
        
        <div className="nation"style={{position:"relative", textAlign:"center",width:"100%"}}>
          <img src={bgurl} style={{position:"relative", textAlign:"center",width:"100%", zIndex:0}}/>
          <div style={{position:"absolute", width:"100%", zIndex:50,  left:"5%", top:"20%"}}>
          <img src={aurl} style={{position:"absolute", width:"85%", zIndex:50, left:"10%", top:"5%"}}/>
          </div>
          <div style={{position:"absolute", width:"100%", zIndex:60, right:"31%" ,top:"15%", color:"white"}}>
          <strong style={{fontSize:"21px"}}>{e.row.ovr}</strong>
          </div>
          <div style={{position:"absolute", width:"100%", zIndex:60, right:"31%" ,top:"22%", color:"white"}}>
          <p style={{fontSize:"12px"}}>{e.row.mainPosition[0]}</p>
          </div>
          <div style={{position:"absolute", width:"100%", zIndex:60, top:"75%", left:"10%",fontSize:"12px"}}>
          <img src={surl} style={{position:"absolute", width:"13%", marginRight:"30px"}}/>
          {e.row.name}
          </div>
          <div style={{position:"absolute", width:"100%", zIndex:70, top:"75%", color:"black"}}>
          <img src="./pngegg.png"/>
          <p style={{fontSize:"16px"}}>{e.row.paySide}</p>
          </div>
        </div>
        
        
		  </div>
    )
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

          <Button sx={{ m: 2}} variant="contained" onClick={() => window.location.reload(false)}>초기화</Button>
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

              {/* 시즌 s */}
              <Grid  sx={{mt:1,mb:3}} >
              <Typography id="input-slider" gutterBottom>
                시즌
                <br/>
                <ToggleButton
                value="check"
                selected={sAllselected}
                size="small"
                onChange={() => {
                  if(!sAllselected){
                    console.log(true);
                    setsAllselected(!sAllselected);
                    let list = new Array();
                    seasonList.map((n)=>{
                      list.push(String(n.seasonId))
                    })
                    setSeason(list);
                  }else{
                    console.log(false);
                    setsAllselected(!sAllselected);
                    setSeason([]);
                  }
                }}
              sx={{mt:1}}
              >전체선택</ToggleButton>
              </Typography>
             
              <ToggleButtonGroup size="small" color='secondary' value={season} onChange={(e, v)=>{
              setSeason(v);
              console.log(v);
            }} aria-label="Small sizes">
                {
                seasonList.map((n,i) => {
                  if(i++ < 24){
                    return (  
                        <ToggleButton sx={{m:0.5}}  aria-label="Small sizes"  key={i} value={String(n.seasonId)} onClick={(e)=>{console.log()
                        }}>
                        <img
                          loading="lazy"
                          width="20"
                          src={n.seasonImg}
                          alt="Season"
                        /> 
                        </ToggleButton> 
                    )
                  }
                })
              }
              </ToggleButtonGroup>
              <ToggleButtonGroup size="small" color='secondary' value={season} onChange={(e, v)=>{
              setSeason(v);
              console.log(v);
              }} aria-label="Small sizes">
               {
                seasonList.map((n,i) => {
                  if(i++ >= 24 && i < 49){
                    return (  
                        <ToggleButton sx={{m:0.5}} aria-label="Small sizes" key={i} value={String(n.seasonId)} onClick={(e)=>{console.log()
                        }}>
                        <img
                          loading="lazy"
                          width="20"
                          src={n.seasonImg}
                          alt="Season"
                        /> 
                        </ToggleButton> 
                    )
                  }
                })
              }
              </ToggleButtonGroup>
              <ToggleButtonGroup size="small" color='secondary' value={season} onChange={(e, v)=>{
              setSeason(v);
              console.log(v);
              }} aria-label="Small sizes">
               {
                seasonList.map((n,i) => {
                  if(i++ >= 48 && i < 73){
                    return (  
                        <ToggleButton sx={{m:0.5}} aria-label="Small sizes" key={i} value={String(n.seasonId)} onClick={(e)=>{console.log()
                        }}>
                        <img
                          loading="lazy"
                          width="20"
                          src={n.seasonImg}
                          alt="Season"
                        /> 
                        </ToggleButton> 
                    )
                  }
                })
              }
              </ToggleButtonGroup>
              <ToggleButtonGroup size="small" color='secondary' value={season} onChange={(e, v)=>{
              setSeason(v);
              console.log(v);
              }} aria-label="Small sizes">
               {
                seasonList.map((n,i) => {
                  if(i++ >= 72 && i < 97){
                    return (  
                        <ToggleButton sx={{m:0.5}} aria-label="Small sizes" key={i} value={String(n.seasonId)} onClick={(e)=>{console.log()
                        }}>
                        <img
                          loading="lazy"
                          width="20"
                          src={n.seasonImg}
                          alt="Season"
                        /> 
                        </ToggleButton> 
                    )
                  }
                })
              }
              </ToggleButtonGroup>
              <ToggleButtonGroup size="small" color='secondary' value={season} onChange={(e, v)=>{
              setSeason(v);
              console.log(v);
              }} aria-label="Small sizes">
               {
                seasonList.map((n,i) => {
                  if(i++ >= 96 && i < 121){
                    return (  
                        <ToggleButton sx={{m:0.5}} aria-label="Small sizes" key={i} value={String(n.seasonId)} onClick={(e)=>{console.log()
                        }}>
                        <img
                          loading="lazy"
                          width="20"
                          src={n.seasonImg}
                          alt="Season"
                        /> 
                        </ToggleButton> 
                    )
                  }
                })
              }
              </ToggleButtonGroup>
            
              </Grid>
              {/* 시즌 e */}

              <Divider sx={{mb:3}} />
              
              {/*포지션 s */}
              <Typography>포지션</Typography>
              <Grid container spacing={0} sx={{  display: 'flex', mt:1,mb:3}}>
              
              <Chip
                label="FW"
                onClick={()=>{
                  if(fw){
                    setMainPositionList({
                      ...mainPositionList,
                      ST: true,
                      RW: true,
                      CF: true,
                      LW: true,
                    });
                  }else{
                    setMainPositionList({
                      ...mainPositionList,
                      ST: false,
                      RW: false,
                      CF: false,
                      LW: false,
                    });
                  }
                  setFw(!fw)
                  
                }}
                sx={{
                  mt: 1,
                  align: "center",
                  color: pink[800],
                  "&.Mui-checked": {
                    color: pink[600]
                  }
                }}
              />

              <Box sx={{ display: "flex", ml: 3 }}>
                <FormControlLabel
                  label="ST"
                  control={
                    <Checkbox
                      sx={{
                        color: pink[800],
                        "&.Mui-checked": {
                          color: pink[600]
                        }
                      }}
                      name="ST"
                      checked={ST}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="LW"
                  control={
                    <Checkbox
                      sx={{
                        color: pink[800],
                        "&.Mui-checked": {
                          color: pink[600]
                        }
                      }}
                      name="LW"
                      checked={LW}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="RW"
                  control={
                    <Checkbox
                      sx={{
                        color: pink[800],
                        "&.Mui-checked": {
                          color: pink[600]
                        }
                      }}
                      name="RW"
                      checked={RW}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="CF"
                  control={
                    <Checkbox
                      sx={{
                        color: pink[800],
                        "&.Mui-checked": {
                          color: pink[600]
                        }
                      }}
                      name="CF"
                      checked={CF}
                      onChange={PhandleChange}
                    />
                  }
                />
              </Box>
              </Grid>

              <Grid container spacing={0} sx={{  display: 'flex', mt:1,mb:3}}>
              
              <Chip
                label="MF"
                onClick={()=>{
                  if(mf){
                    setMainPositionList({
                      ...mainPositionList,
                      CAM: true,
                      CM: true,
                      LM: true,
                      RM: true,
                      CDM: true,
                    });
                  }else{
                    setMainPositionList({
                      ...mainPositionList,
                      CAM: false,
                      CM: false,
                      LM: false,
                      RM: false,
                      CDM: false,
                    });
                  }
                  setMf(!mf)
                  
                }}
                sx={{
                  mt: 1,
                  align: "center",
                  color: green[800],
                  "&.Mui-checked": {
                    color: green[600]
                  }
                }}
              />

              <Box sx={{ display: "flex", ml: 3 }}>
                <FormControlLabel
                  label="CAM"
                  control={
                    <Checkbox
                      sx={{
                        color: green[800],
                        "&.Mui-checked": {
                          color: green[600]
                        }
                      }}
                      name="CAM"
                      checked={CAM}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="CM"
                  control={
                    <Checkbox
                      sx={{
                        color: green[800],
                        "&.Mui-checked": {
                          color: green[600]
                        }
                      }}
                      name="CM"
                      checked={CM}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="RM"
                  control={
                    <Checkbox
                      sx={{
                        color: green[800],
                        "&.Mui-checked": {
                          color: green[600]
                        }
                      }}
                      name="RM"
                      checked={RM}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="LM"
                  control={
                    <Checkbox
                      sx={{
                        color: green[800],
                        "&.Mui-checked": {
                          color: green[600]
                        }
                      }}
                      name="LM"
                      checked={LM}
                      onChange={PhandleChange}
                    />
                  }
                />
                 <FormControlLabel
                  label="CDM"
                  control={
                    <Checkbox
                      sx={{
                        color: green[800],
                        "&.Mui-checked": {
                          color: green[600]
                        }
                      }}
                      name="CDM"
                      checked={CDM}
                      onChange={PhandleChange}
                    />
                  }
                />
              </Box>
           

              </Grid>

              <Grid container spacing={0} sx={{  display: 'flex', mt:1,mb:3}}>
              
              <Chip
                label="DF"
                onClick={()=>{
                  if(df){
                    setMainPositionList({
                      ...mainPositionList,
                      LWB: true,
                      CB: true,
                      RWB: true,
                      LB: true,
                      RB: true,
                    });
                  }else{
                    setMainPositionList({
                      ...mainPositionList,
                      LWB: false,
                      CB: false,
                      RWB: false,
                      LB: false,
                      RB: false,
                    });
                  }
                  setDf(!df)
                  
                }}
                sx={{
                  mt: 1,
                  align: "center",
                  color: blue[800],
                  "&.Mui-checked": {
                    color: blue[600]
                  }
                }}
              />

              <Box sx={{ display: "flex", ml: 3 }}>
                <FormControlLabel
                  label="LWB"
                  control={
                    <Checkbox
                      sx={{
                        color: blue[800],
                        "&.Mui-checked": {
                          color: blue[600]
                        }
                      }}
                      name="LWB"
                      checked={LWB}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="CB"
                  control={
                    <Checkbox
                      sx={{
                        color: blue[800],
                        "&.Mui-checked": {
                          color: blue[600]
                        }
                      }}
                      name="CB"
                      checked={CB}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="RWB"
                  control={
                    <Checkbox
                      sx={{
                        color: blue[800],
                        "&.Mui-checked": {
                          color: blue[600]
                        }
                      }}
                      name="RWB"
                      checked={RWB}
                      onChange={PhandleChange}
                    />
                  }
                />
                <FormControlLabel
                  label="LB"
                  control={
                    <Checkbox
                      sx={{
                        color: blue[800],
                        "&.Mui-checked": {
                          color: blue[600]
                        }
                      }}
                      name="LB"
                      checked={LB}
                      onChange={PhandleChange}
                    />
                  }
                />
                 <FormControlLabel
                  label="RB"
                  control={
                    <Checkbox
                      sx={{
                        color: blue[800],
                        "&.Mui-checked": {
                          color: blue[600]
                        }
                      }}
                      name="RB"
                      checked={RB}
                      onChange={PhandleChange}
                    />
                  }
                />
              </Box>
              </Grid>

              <Grid container spacing={0} sx={{  display: 'flex', mt:1,mb:3}}>
              
              <Chip
                label="GK"
                onClick={()=>{
                  if(mainPositionList.GK){
                    setMainPositionList({
                      ...mainPositionList,
                      GK: false,
                    });
                  }else{
                    setMainPositionList({
                      ...mainPositionList,
                      GK: true,
                    });
                  }
                  
                  
                }}
                sx={{
                  mt: 1,
                  align: "center",
                  color: yellow[800],
                  "&.Mui-checked": {
                    color: yellow[600]
                  }
                }}
              />

              <Box sx={{ display: "flex", ml: 3 }}>
                <FormControlLabel
                  label="GK"
                  control={
                    <Checkbox
                      sx={{
                        color: yellow[800],
                        "&.Mui-checked": {
                          color: yellow[600]
                        }
                      }}
                      name="GK"
                      checked={GK}
                      onChange={PhandleChange}
                    />
                  }
                />

              </Box>
              </Grid>
              {/*포지션 e */}

              <Divider sx={{mb:3}} />

              <Grid container spacing={0} sx={{ display: 'flex', mt:2,mb:3}}>
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
                      return ( <MenuItem value={++i} key={i}>{i}</MenuItem> )
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
                      return ( <MenuItem value={++i} key={i}>{i}</MenuItem> )
                    })
                  }

                </Select>
              </FormControl>
              </Box>

              </Grid>

              <Divider sx={{mb:3}} />

              <Grid container spacing={0} sx={{  display: 'flex', mt:2,mb:3}}>

               {/* 개인기 */}          
            <Box sx={{ '& > legend': { mt: 2 }, boxShadow: 2}}>
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
              limitTags={2}
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
              {/* 팀컬러 종류 */}
              <FormControl  sx={{ width: 150 ,boxShadow: 2,  pr: 1, pl: 1 , ml:2, mr:2}}>
              <InputLabel id="demo-simple-select-standard-label">팀컬러 종류</InputLabel>
              <Select
                label="팀컬러 종류"
                value={tcType}
                onChange={(e)=>{
                  setTcType(e.target.value);
                  searchTC(e.target.value);
                }}
              >
                <MenuItem value="">
                  <em>전체</em>
                </MenuItem>
                    <MenuItem value="club">클럽</MenuItem>
                    <MenuItem value="nation">국가</MenuItem>
                    <MenuItem value="special">스페셜</MenuItem>
                    <MenuItem value="relation">관계</MenuItem>
              </Select>
            </FormControl>
             

                {/* 팀컬러 */}
                <Autocomplete
                multiple
                id="teamcolor"
                sx={{ width: 400 ,boxShadow: 2, pr: 1, pl: 1}}
                options={teamcolorList}
                limitTags={1}
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
                
              />
            
          
              </Grid>

            <Divider sx={{mb:3}} />

            <Grid container spacing={0} sx={{  display: 'flex', mt:2, mb:3}}>
            {/* 상세 스탯1 */}
            <FormControl  sx={{ width: 150 ,boxShadow: 2,  pr: 1, pl: 1 , mr:1}}>
              <InputLabel id="demo-simple-select-standard-label">상세 스탯1</InputLabel>
              <Select
                label="상세 스탯1"
                value={detailStat_1}
                onChange={(e)=>{
                  let data = JSON.parse(e.target.value);
                  setDetailStat({
                    ...detailStat,
                    [detailStat_1]: [0,200],
                  });
                  setDetailStat_1v([0,200]);
                  setDetailStat_1(data[0])
                  setDetailStat_1Nm(data[1])
                }}
              >
                <MenuItem value='["",""]'>
                  <em></em>
                </MenuItem>
                {
                  Object.entries(detailStatNm).map((data,i)=>{
                    return(
                    <MenuItem value={JSON.stringify(data)} key={i} >{data[1]}</MenuItem>
                    )
                  })
                }
              </Select>
            </FormControl>

            <Box  sx={{ width: 180 ,boxShadow: 2, pr: 3, pl: 1}}>
              <Typography id="input-slider" gutterBottom>
              {detailStat_1Nm}
              </Typography>
              <Grid container spacing={3}>
              <Grid item xs sx={{ minWidth: "25%" }}  >
              <Input
                value={detailStat_1v[0]}
                name={detailStat_1}
                size="small"
                onChange={(e)=>{

                }}
                inputProps={{
                  min: 0,
                  max: 200,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              <Grid item xs sx={{ minWidth: "50%" }} >
                <Slider
                  min={0}
                  max={200}
                  value={detailStat_1v}
                  name={detailStat_1}
                  onChange={(e, newValue) => {
                  setDetailStat_1v(newValue);
                  setDetailStat({
                    ...detailStat,
                    [e.target.name]: e.target.value,
                  });
                  }}
                  valueLabelDisplay="auto"
                />
                </Grid>
                <Grid item xs sx={{ minWidth: "25%" }}>
                <Input
                value={detailStat_1v[1]}
                name={detailStat_1}
                size="small"
                onChange={(e)=>{

                }}
                inputProps={{
                  min: 0,
                  max: 200,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              </Grid>
              </Box>

            {/* 상세 스탯2 */}
            <FormControl  sx={{ width: 150 ,boxShadow: 2,  pr: 1, pl: 1 ,ml:1, mr:1}}>
              <InputLabel id="demo-simple-select-standard-label">상세 스탯2</InputLabel>
              <Select
                label="상세 스탯2"
                value={detailStat_2}
                onChange={(e)=>{
                  let data = JSON.parse(e.target.value);
                  setDetailStat({
                    ...detailStat,
                    [detailStat_2]: [0,200],
                  });
                  setDetailStat_2v([0,200]);
                  setDetailStat_2(data[0])
                  setDetailStat_2Nm(data[1])
                }}
              >
                <MenuItem value='["",""]'>
                  <em></em>
                </MenuItem>
                {
                  Object.entries(detailStatNm).map((data,i)=>{
                    return(
                    <MenuItem value={JSON.stringify(data)} key={i} >{data[1]}</MenuItem>
                    )
                  })
                }
              </Select>
            </FormControl>

            <Box  sx={{ width: 180 ,boxShadow: 2, pr: 3, pl: 1}}>
              <Typography id="input-slider" gutterBottom>
              {detailStat_2Nm}
              </Typography>
              <Grid container spacing={3}>
              <Grid item xs sx={{ minWidth: "25%" }}  >
              <Input
                value={detailStat_2v[0]}
                name={detailStat_2}
                size="small"
                onChange={(e)=>{

                }}
                inputProps={{
                  min: 0,
                  max: 200,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              <Grid item xs sx={{ minWidth: "50%" }} >
                <Slider
                  min={0}
                  max={200}
                  value={detailStat_2v}
                  name={detailStat_2}
                  onChange={(e, newValue) => {
                  setDetailStat_2v(newValue);
                  setDetailStat({
                    ...detailStat,
                    [e.target.name]: e.target.value,
                  });
                  }}
                  valueLabelDisplay="auto"
                />
                </Grid>
                <Grid item xs sx={{ minWidth: "25%" }}>
                <Input
                value={detailStat_2v[1]}
                name={detailStat_2}
                size="small"
                onChange={(e)=>{

                }}
                inputProps={{
                  min: 0,
                  max: 200,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              </Grid>
              </Box>

              {/* 상세 스탯3 */}
              <FormControl  sx={{ width: 150 ,boxShadow: 2,  pr: 1, pl: 1 ,ml:1, mr:1}}>
              <InputLabel id="demo-simple-select-standard-label">상세 스탯3</InputLabel>
              <Select
                label="상세 스탯3"
                value={detailStat_3}
                onChange={(e)=>{
                  let data = JSON.parse(e.target.value);
                  setDetailStat({
                    ...detailStat,
                    [detailStat_3]: [0,200],
                  });
                  setDetailStat_3v([0,200]);
                  setDetailStat_3(data[0])
                  setDetailStat_3Nm(data[1])
                }}
              >
                <MenuItem value='["",""]'>
                  <em></em>
                </MenuItem>
                {
                  Object.entries(detailStatNm).map((data,i)=>{
                    return(
                    <MenuItem value={JSON.stringify(data)} key={i} >{data[1]}</MenuItem>
                    )
                  })
                }
              </Select>
            </FormControl>

            <Box  sx={{ width: 180 ,boxShadow: 2, pr: 3, pl: 1}}>
              <Typography id="input-slider" gutterBottom>
              {detailStat_3Nm}
              </Typography>
              <Grid container spacing={3}>
              <Grid item xs sx={{ minWidth: "25%" }}  >
              <Input
                value={detailStat_3v[0]}
                name={detailStat_3}
                size="small"
                onChange={(e)=>{

                }}
                inputProps={{
                  min: 0,
                  max: 200,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              <Grid item xs sx={{ minWidth: "50%" }} >
                <Slider
                  min={0}
                  max={200}
                  value={detailStat_3v}
                  name={detailStat_3}
                  onChange={(e, newValue) => {
                  setDetailStat_3v(newValue);
                  setDetailStat({
                    ...detailStat,
                    [e.target.name]: e.target.value,
                  });
                  }}
                  valueLabelDisplay="auto"
                />
                </Grid>
                <Grid item xs sx={{ minWidth: "25%" }}>
                <Input
                value={detailStat_3v[1]}
                name={detailStat_3}
                size="small"
                onChange={(e)=>{

                }}
                inputProps={{
                  min: 0,
                  max: 200,
                  type: 'number',
                  'aria-labelledby': 'input-slider',
                }}
              />
              </Grid>
              </Grid>
              </Box>

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
                  const isItemSelected = isSelected(row.id);
                  const labelId = `enhanced-table-checkbox-${index}`;

                  return (
                    <TableRow
                      hover
                      onClick={(event) => handleClick(event, row.id)}
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
                        style={{height: 150,width: 130, overflow: "hidden", backgroundImage: "./season.{row.id}"}}

                      >
                        {
                          setImage({row})
                        }
                        
                      </TableCell>
                      <TableCell align="center">{row.name}</TableCell>
                      <TableCell align="center">{row.paySide}</TableCell>
                      <TableCell align="center">{row.ovr}</TableCell>
                      <TableCell align="center">{row.lfoot}</TableCell>
                      <TableCell align="center">{row.rfoot}</TableCell>
                      <TableCell align="center">{row.pay}</TableCell>
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


