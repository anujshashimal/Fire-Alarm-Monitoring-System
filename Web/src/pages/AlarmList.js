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
                // if (err) throw err;

                this.setState({ posts: data })
            })

            .then(async() => {
                this.setState({ tableRows:this.assemblePosts(), isLoading:true })
                console.log(this.state.tableRows);
            })
    }

    // getAllSensorNotify = async () => {
    //     await axios.get('http://localhost:5000/api/sensor/')
    //         .then(response => response.data)
    //         .then(data => {
    //             data.map( (alertdet) => {
    //                 if(alertdet.co2Level >5 && alertdet.smokeLevel > 5){
    //                     console.log('co2Level and smoke Increased')
    //                     this.setState({notify:true})
    //                 }else if(alertdet.co2Level> 5){
    //                     console.log('')
    //                 }else if(alertdet.smokeLevel){
    //                     console.log('')
    //                 }else if(''){
    //                     console.log('')
    //                 }
    //             })
    //
    //             // if (err) throw err;
    //
    //             this.setState({ posts: data })
    //         })
    //
    //         .then(async() => {
    //             this.setState({ tableRows:this.assemblePosts(), isLoading:true })
    //             console.log(this.state.tableRows);
    //
    //         })
    // }


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

