import React from 'react';
import {
  MDBEdgeHeader,
  MDBFreeBird,
  MDBContainer,
  MDBCol,
  MDBRow,
  MDBCardBody,
  MDBIcon,
  MDBCard,
  MDBCardTitle,
  MDBCardImage,
  MDBCardText,
  MDBAnimation,
  MDBNavLink

} from 'mdbreact';
import './HomePage.css';

import alarmlogo from '../assets/coventry_alarms.png';
import reportlogo from '../assets/1770521.png'

//class home page starts
class HomePage extends React.Component {
  scrollToTop = () => window.scrollTo(0, 0);

  render() {
    return (
        <>
          <MDBEdgeHeader color='indigo darken-3'  />
          <div className='mt-3 mb-5'>
            <MDBFreeBird>
              <MDBRow>
                <MDBCol
                    md='10'
                    className='mx-auto float-none white z-depth-1 py-2 px-2'
                >
                  <MDBCardBody className='text-center'>
                    <h2 className='h2-responsive mb-4'>
                      <strong className='font-weight-bold'>
                        Fire Alarm Monitoring System
                      </strong>
                    </h2>
                    <MDBRow />

                  </MDBCardBody>
                </MDBCol>
              </MDBRow>
            </MDBFreeBird>
            <MDBContainer>
              <MDBRow>
                <MDBCol md='12' className='mt-4'>

                  <hr className='my-5' />

                  <MDBRow id='categories'>

                    <MDBCol md='6'>
                      <MDBAnimation reveal type='fadeInDown'>
                        <MDBCard cascade className='my-3 grey lighten-4' style={{height: '26rem'}}>
                          <MDBCardImage
                              cascade
                              className='img-fluid'
                              src={alarmlogo}
                          />

                          <MDBCardBody cascade className='text-center'>
                            <MDBCardTitle>
                              <MDBIcon
                                  icon='bell'
                                  solid
                                  className='pink-text pr-2' />
                              <strong>Alarms</strong>
                            </MDBCardTitle>
                            <MDBCardText>
                              Ready-to-use components that you can use in your
                              applications. Both basic and extended versions!
                            </MDBCardText>
                            <MDBNavLink
                                tag='button'
                                to='/AlarmList'
                                color='mdb-color'
                                className='btn btn-outline-mdb-color btn-sm btn-rounded d-inline'
                                onClick={this.scrollToTop}
                            >
                              Add New
                            </MDBNavLink>
                          </MDBCardBody>
                        </MDBCard>
                      </MDBAnimation>
                    </MDBCol>
                    <MDBCol md='6'>
                      <MDBAnimation reveal type='fadeInRight'>
                        <MDBCard cascade className='my-3 grey lighten-4' style={{height: '26rem'}}>
                          <MDBCardImage
                              cascade
                              className='img-fluid'
                              src={reportlogo}
                          />
                          <MDBCardBody cascade className='text-center'>
                            <MDBCardTitle>
                              <MDBIcon
                                  icon='file-invoice'
                                  solid
                                  className='green-text pr-2' />
                              <strong>Reports</strong>
                            </MDBCardTitle>
                            <MDBCardText>
                              Advanced components such as charts, carousels,
                              tooltips and popovers. All in Material Design
                              version.
                            </MDBCardText>
                            <MDBNavLink
                                tag='button'
                                to='/advanced'
                                color='mdb-color'
                                className='btn btn-outline-mdb-color btn-sm btn-rounded d-inline'
                                onClick={this.scrollToTop}
                            >
                              Add New
                            </MDBNavLink>
                          </MDBCardBody>
                        </MDBCard>
                      </MDBAnimation>
                    </MDBCol>
                  </MDBRow>
                  <MDBRow id='categories'>
                    <MDBCol md='4'  style={{marginLeft:"24rem"}}>
                      <MDBAnimation reveal type='fadeInLeft'>
                      </MDBAnimation>
                    </MDBCol>
                  </MDBRow>
                </MDBCol>
              </MDBRow>
            </MDBContainer>
          </div>
        </>
    );
  }
}

export default HomePage;
