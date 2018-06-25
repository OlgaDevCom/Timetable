//
//  AlertInfoDir.swift
//  timetable
//
//  Created by  Stanislav Topanov on 6/25/18.
//  Copyright © 2018  Stanislav Topanov. All rights reserved.
//

import UIKit


class AlertInfoDir: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var navtxt: UINavigationItem!
    internal var trans = Trans([:])
    var size = Int()
    var dir = String()
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return (trans?.list.count)!
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell =  tableView.dequeueReusableCell(withIdentifier: "CellInfo") as! CellInfo
        if size == indexPath.row
        {
           cell.time.text = trans?.listat["\(indexPath.row)"] as! String
        }else{
           cell.time.text = trans?.listto["\(indexPath.row)"] as! String
        }
       
        cell.title_.text = getKey(value: "\(indexPath.row)")
        return cell
    }
    
    @IBAction func back_(_ sender: UIBarButtonItem) {
        dismiss(animated: true, completion: nil)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        size = (trans?.list.count)! - 1
        dir = "\(getKey(value: "0")) - \(getKey(value: "\(size)"))"
        navtxt.title = dir
        print("sdfsdf \(trans?.list)")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
       
    }
    private func getKey(value : String) -> String
    {
        var key = String()
        for (kind, numbers) in (trans?.list)! {
            if value == "\(numbers)"
            {
                key = kind
            }
        }
        return key
    }
   
    
}
