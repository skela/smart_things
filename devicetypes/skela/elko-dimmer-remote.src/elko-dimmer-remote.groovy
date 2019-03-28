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
    definition (name: "Elko Dimmer Remote", namespace: "skela", author: "Alek Slater") {
        capability "Actuator"                
        capability "Momentary"
        capability "Switch Level"
        capability "Button"
        //capability "Light"
	
	//Raw code from elko dimmer: 01 0104 0101 00 04 0000 0003 0006 0008 01 0003
    //Raw code from elko dimrem: 01 0104 0104 00 02 0000 0003 03 0003 0006 0008 
	//fingerprint profileId: "0104", inClusters: "0000, 0003, 0006, 0008", outClusters: "0003"
    fingerprint profileId: "0104", inClusters: "0000, 0003", outClusters: "0003, 0006, 0008"
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
	def date = new Date()
	def timestamp = date.getTime()/1000
	log.debug "pushing ${timestamp}"
	//sendEvent(name: "switch", value: "on", isStateChange: true, displayed: false)
	//sendEvent(name: "switch", value: "off", isStateChange: true, displayed: false)
	//sendEvent(name: "momentary", value: "pushed", isStateChange: true)
    sendEvent(name:"level",value:timestamp)
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
    if (description=="catchall: 0104 0006 01 01 0140 00 1499 01 00 0000 02 00 ")
    {
    	push()
        //def ev = createEvent(name: "button", value: "pushed", data: [])
        //sendEvent(ev)
        //return ev
        
    }    
}
