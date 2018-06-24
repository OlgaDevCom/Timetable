//
//  ViewController.swift
//  timetable
//
//  Created by  Stanislav Topanov on 6/23/18.
//  Copyright © 2018  Stanislav Topanov. All rights reserved.
//

import UIKit
import SwiftyJSON
import Firebase
import FirebaseFirestore

class ViewController: UIViewController {
   
    
    
     var arList = Array<Trans>()
     var arDir = Array<String>()

    @IBOutlet weak var edit2: SearchTextField!
    @IBOutlet weak var edit: SearchTextField!
    
    
    @IBOutlet weak var btSearch: UIButton!
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    var arr =  Array<String>()
    override func viewDidLoad() {
        super.viewDidLoad()
        arDir.append("")
        arDir.append("")

        
        getStations()
        edit.filterStrings(arr)
        edit.theme.bgColor = UIColor.white
        edit2.theme.bgColor = UIColor.white
        edit.theme.font = UIFont(name: "Avenir-Light", size: 17.0)!
        edit2.theme.font = UIFont(name: "Avenir-Light", size: 17.0)!
        edit2.filterStrings(arr)
      
    }
    
    
    @IBAction func reversEdit(_ sender: Any) {
        
    }
    
    
    @IBAction func getFirst(_ sender: UITextField) {
//        print(sender.text!)
//        let txt = sender.text!
//        let f = arr.contains(txt)
//        print(f)

    }
    
    @IBAction func getSecond(_ sender: UITextField) {
       // print(sender.text!)
    }
    
    
    @IBAction func search(_ sender: UIButton) {
        
        let str1 = edit.text!
        let d1 = arr.contains(str1)
        let str2 = edit2.text!
        let d2 = arr.contains(str2)
        
        print(d1)
        print(d2)
        print(str1)
        print(str2)

        if d1 && d2
        {
            arDir[0] = str1
            arDir[1] = str2
              getFromFirestore()
        }

    }
    
    
    private func getFromFirestore()
    {
        let db = Firestore.firestore()
        let settings = FirestoreSettings()
        settings.isPersistenceEnabled = true
        db.settings = settings

        let citiesRef = db.collection("poezd")

        let query = citiesRef.whereField("list.\(arDir[0])", isGreaterThanOrEqualTo: -1)
        query
            .getDocuments() { (FIRDataSnapshot, err) in
                if let err = err {
                    print("Error getting documents: \(err)")
                } else {
  
                    for document in FIRDataSnapshot!.documents {
          
                        let ar = Trans(document.data() as [String : AnyObject])
                        
                        let list = (ar?.list)!
                        
                       

                      
                            if ar?.dir! != "" && ar?.list[self.arDir[1]] != nil
                            {
                                let id = list[self.arDir[0]]! as! Int
                                let id2 = list[self.arDir[1]]! as! Int
                                if Double(id) < Double(id2)
                                {
                                self.arList.append((ar)!)
                                    
                                }

                            }
                       
                       
      
                    }
                    
                    
                     self.ToAlertDir()
                }
        }
        
    }
    
    private func ToAlertDir()
    {
        if let vc = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "alert_dir") as? AlertDir
        {
            vc.arrDir = self.arDir
            vc.arrTrans = self.arList
            self.present(vc, animated: true, completion: nil)
            
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
    }
 
    
  func getStations()
  {
    if let path = Bundle.main.path(forResource: "stations", ofType: "json") {
        do {
            
            let data = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
//            let jsonResult = try JSONSerialization.jsonObject(with: data, options: .mutableLeaves)
//           //   print(data)
//           //  print(jsonResult)
            do {
                
               let json = try JSON(data: data)
                for (index,subJson):(String, JSON) in json {
                  //  print(subJson["station"])
                    arr.append("\(subJson["station"])")
                }
               print(arr.count)
            } catch {
                print(error)
            }
        } catch {
            // handle error
        }
    }
  }
    
}

