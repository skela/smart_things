/**
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */

metadata {
    definition (name: "Aqara Button", namespace: "skela", author: "Alek Slater") {
        capability "Actuator"                
        capability "Momentary"
        //capability "Switch Level"
        capability "Button"
        //capability "Light"
	
		// Aqara Button - original revision - model WXKG11LM
        fingerprint endpointId: "01", profileId: "0104", deviceId: "5F01", inClusters: "0000,FFFF,0006", outClusters: "0000,0004,FFFF", manufacturer: "LUMI", model: "lumi.sensor_switch.aq2", deviceJoinName: "Aqara Button WXKG11LM"
        // Aqara Button - new revision - model WXKG12LM
        fingerprint endpointId: "01", profileId: "0104", deviceId: "5F01", inClusters: "0000,0001,0006,0012", outClusters: "0000", manufacturer: "LUMI", model: "lumi.sensor_switch.aq3", deviceJoinName: "Aqara Button WXKG12LM"
        fingerprint endpointId: "01", profileId: "0104", deviceId: "5F01", inClusters: "0000,0001,0006,0012", outClusters: "0000", manufacturer: "LUMI", model: "lumi.sensor_swit", deviceJoinName: "Aqara Button WXKG12LM"
        // Aqara Smart Light Switch - single button - model WXKG03LM
        fingerprint endpointId: "01", profileId: "0104", deviceId: "5F01", inClusters: "0000,0003,0019,0012,FFFF", outClusters: "0000,0003,0004,0005,0019,0012,FFFF", manufacturer: "LUMI", model: "lumi.sensor_86sw1lu", deviceJoinName: "Aqara Switch WXKG03LM"
        fingerprint endpointId: "01", profileId: "0104", deviceId: "5F01", inClusters: "0000,0003,0019,0012,FFFF", outClusters: "0000,0003,0004,0005,0019,0012,FFFF", manufacturer: "LUMI", model: "lumi.sensor_86sw1", deviceJoinName: "Aqara Switch WXKG03LM"
        // Aqara Smart Light Switch - dual button - model WXKG02LM
        fingerprint endpointId: "01", profileId: "0104", deviceId: "5F01", inClusters: "0000,0003,0019,0012,FFFF", outClusters: "0000,0003,0004,0005,0019,0012,FFFF", manufacturer: "LUMI", model: "lumi.sensor_86sw2Un", deviceJoinName: "Aqara Switch WXKG02LM"
    }

    // UI tile definitions
	tiles(scale: 2){
		multiAttributeTile(name:"switch", type: "generic", width: 6, height: 4, canChangeIcon: true){
			tileAttribute("device.switch", key: "PRIMARY_CONTROL") {
				attributeState("off", label: 'Push', action: "momentary.push", backgroundColor: "#ffffff", nextState: "on")
				attributeState("on", label: 'Push', action: "momentary.push", backgroundColor: "#00a0dc")
			}	
		}
		main "switch"
		details "switch"
}
}

def push() {
	//def date = new Date()
	//def timestamp = date.getTime()/1000
	//log.debug "pushing ${timestamp}"
	//sendEvent(name: "switch", value: "on", isStateChange: true, displayed: false)
	//sendEvent(name: "switch", value: "off", isStateChange: true, displayed: false)
	sendEvent(name: "button", value: "pushed", isStateChange: true)
    //sendEvent(name:"level",value:timestamp)
}

def on() {
	log.debug "im on"
	push()
}

def off() {
	log.debug "im off"
	push()
}

// Parse incoming device messages to generate events
def parse(String description) 
{
    log.debug "description is $description"
    if (description=="on/off: 1")
    {
    	push()
        //def ev = createEvent(name: "button", value: "pushed", data: [])
        //sendEvent(ev)
        //return ev
        
    }    
}
