//
//  Trans.swift
//  timetable
//
//  Created by  Stanislav Topanov on 6/23/18.
//  Copyright © 2018  Stanislav Topanov. All rights reserved.
//

import Foundation
import FirebaseDatabase


class Trans :  NSObject{
    var dir: String! = ""
    var url: String = ""
    var list: [String:Any] = [:]
    var listat: [String:Any] = [:]
    var listto: [String:Any] = [:]


    init?(_ snapshot: [String : AnyObject]) {
        if snapshot["dir"] as? String != nil{
            dir = snapshot["dir"] as? String!
            url = (snapshot["url"] as? String)!
            list = (snapshot["list"] as? [String:Any])!
            listat = (snapshot["listat"] as? [String:Any])!
            listto = (snapshot["listto"] as? [String:Any])!
        }
        
    }
}
