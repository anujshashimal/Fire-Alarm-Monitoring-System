import React, { Component } from 'react';
import { MDBDataTable, Row, Col, Card, CardBody } from 'mdbreact';

import axios from 'axios';
const JSSERVER_CONFIG = { host: "htpp://127.0.0.1", port: 4000};

class TableSectionInbound extends Component {

    constructor(props) {

        super(props);
        this.state= {
            posts: [],
            isLoading:true,
            tableRows: [],
            notify : false

        };
    }

    componentWillUnmount() {
        clearInterval(this.getAllSensorDet());
    }
    componentDidMount() {
        this.getAllSensorDet();
        this.interval = setInterval(this.getAllSensorDet, 1000);
    }

// `${JSSERVER_CONFIG.host}:${JSSERVER_CONFIG.port}/Sensors`

    getAllSensorDet = async () => {
        await axios.get(`http://localhost:5000/api/sensors`)
            .then(response => response.data)
            .then(data => {
                console.log(data);
                if(data.co2Level > 4){
                    alert("Error")
                    console.log('24')
                }else{
                    console.log('2')
                    this.setState({ posts: data })
                }

            })

            .then(async() => {
                this.setState({ tableRows:this.assemblePosts(), isLoading:true })
                console.log(this.state.tableRows);
            })
    }

    //MAPPING SENSOR APP DETAILS TO THE RELAVENT TABLE DATA
    assemblePosts= () => {

        let posts =this.state.posts.map((post) => {
            return (
                {
                    id: post.id,
                    status: post.status,
                    co2Level: post.co2Level,
                    smokeLevel: post.smokeLevel,
                    floorNo : post.floorNo,
                    roomNo : post.roomNo
                }
            )

        });

        return posts;
    }


    render() {

        const data = {
            columns: [
                {
                    label:'id',
                    field:'id',
                },
                {
                    label:'status',
                    field:'status',
                },
                {
                    label:'co2Level',
                    field:'co2Level',
                },
                {
                    label:'smokeLevel',
                    field:'smokeLevel',
                },
                {
                    label:'roomNo',
                    field:'roomNo',
                },
                {
                    label:'floorNo',
                    field:'floorNo',
                },
            ],
            rows:this.state.tableRows,
        }

        return (
            <Col md="12">
                <Card>
                    <CardBody>
                        <MDBDataTable
                            data={data}
                        />
                    </CardBody>
                </Card>
            </Col>
        )
    }
}




export default TableSectionInbound;

