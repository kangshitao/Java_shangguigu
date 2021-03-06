package com.atguigu.team.view;

import com.atguigu.team.domain.*;
import com.atguigu.team.service.*;

/**
 * @author Kangshitao
 * @date 2021年3月28日 下午5:46
 */
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    /**
     * 主界面显示及控制方法
     */
    public void enterMainMenu(){
        boolean loopFlag = true;
        char key = '0';
        while(loopFlag){
            if(key != '1') listAllEmployees();
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            System.out.println();
            key = TSUtility.readMenuSelection();
            switch (key){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("确认是否退出(Y/N)：");
                    char isExit = TSUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }
        }
    }

    /**
     * 以表格形式列出公司所有成员
     */
    private void listAllEmployees(){
        System.out.println("\n-------------------------------开发团队调度软件--------------------------------\n");
        Employee[] emp = listSvc.getALLEmployees();
        if(emp.length == 0){
            System.out.println("没有客户记录");
        }else{
            System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        }
        for(Employee e: emp){
            System.out.println(""+e);
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * 显示团队成员列表操作
     */
    private void getTeam(){
        System.out.println("\n--------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if(team.length == 0){
            System.out.println("开发团队目前没有成员！");
        }else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        }
        for(Programmer p : team){
            System.out.println(p.showInfo());
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     *实现添加成员操作
     */
    private void addMember(){
        System.out.println("---------------------添加成员---------------------");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();
        try{
            Employee e = listSvc.getEmployee(id);
            teamSvc.addMember(e);
            System.out.println("添加成功");
        }catch(TeamException e){
            System.out.println("添加失败"+e.getMessage());
        }
        TSUtility.readReturn();
    }

    /**
     *实现删除成员操作
     */
    private void deleteMember(){
        System.out.println("---------------------删除成员---------------------");
        System.out.print("请输入要删除员工的TID：");
        int id = TSUtility.readInt();
        System.out.print("确认是否删除(Y/N)：");
        char idDelete = TSUtility.readConfirmSelection();
        if(idDelete=='N'){
            return;
        }
        try{
            teamSvc.removeMember(id);
            System.out.println("delete successfully!");
        }catch (TeamException e){
            System.out.println("delete failed!"+e.getMessage());
        }
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView team = new TeamView();
        team.enterMainMenu();
    }
}
