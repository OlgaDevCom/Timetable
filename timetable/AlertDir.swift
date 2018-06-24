//
//  AlertDir.swift
//  timetable
//
//  Created by  Stanislav Topanov on 6/24/18.
//  Copyright © 2018  Stanislav Topanov. All rights reserved.
//

import UIKit

class AlertDir: UIViewController , UITableViewDelegate, UITableViewDataSource{
    
    
    internal var arrDir = Array<String>()
    internal var arrTrans = Array<Trans>()

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arrTrans.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        
        
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell") as! CellDir
        let list = arrTrans[indexPath.row].list as Dictionary
        let listat = arrTrans[indexPath.row].listat as Dictionary
        let listto = arrTrans[indexPath.row].listto as Dictionary


        //let idTranc = [-1,-1]
        let id = list[arrDir[0]]!
        let id2 = list[arrDir[1]]!
        let tm = arrTrans[indexPath.row].listto["\(String(describing: id))"]
    
        let indLast = arrTrans[indexPath.row].list.count - 1
         print("sdfsd \(indLast)")
        
        let firsttKey =  Array(list)[0].key
        let lastKey = Array(list)[indLast].key
        getKey(dict: list, value: "0")
        
        
        print(listat["\(id)"]!)
        print(listto["\(id2)"]!)
       
      //  let lastStat = arrTrans[indexPath.row].list
        cell.tm2.text = listat["\(id2)"]! as! String
        cell.tm.text = listto["\(id)"]! as! String
        cell.dir.text = arrDir[0]
        cell.dir2.text = arrDir[1]
        cell.direction.text = "\(getKey(dict: list, value: "0")) - \(getKey(dict: list, value: "\(indLast)"))"
        
        
        return cell
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
          print("arrDir \(arrDir[0])")
           print("arrDir \(arrDir[1])")
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    private func getKey(dict : Dictionary<String, Any>, value : String) -> String
    {
        var key = String()
        for (kind, numbers) in dict {
            if value == "\(numbers)"
            {
              key = kind
                   print("\(kind) \(numbers)")
            }
       
        }
        
        
        return key
    }
    
  
}

