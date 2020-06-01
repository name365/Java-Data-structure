package github3;

import java.util.Scanner;

public class HashTable {
	public static void main(String[] args) {
		//创建哈希表
		HashTab hashTab = new HashTab(7);
		
		//写一个简单的菜单
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("雇员管理系统:");
			System.out.println("add : 添加雇员");
			System.out.println("list: 显示雇员");
			System.out.println("find: 查找雇员");
			System.out.println("del : 删除雇员");
			System.out.println("exit: 退出系统");
			
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.print("输入id:");
				int id = scanner.nextInt();
				System.out.print("输入名字:");
				String name = scanner.next();
				//创建雇员
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "find":
				System.out.print("请输入需要查找的id:");
				id = scanner.nextInt();
				hashTab.findEmpId(id);
				break;
			case "del": 
                System.out.print("请输入雇员的id:");
                id = scanner.nextInt();
                hashTab.delEmpId(id);
                break;			
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
	}
}
//创建HashTab
class HashTab{
	private EmpLink[] empLinkArry;
	private int size; //表示有多少条链表
	
	//构造器
	public HashTab(int size){
		this.size = size;
		//初始化empLinkArry
		empLinkArry = new EmpLink[size];
		//分别初始化每个链表,很重要！！！！
		for(int i = 0; i < size; i++) {
			empLinkArry[i] = new EmpLink();
		}
	}
	
	//添加雇员
	public void add(Emp emp){
		//根据员工的ID,得到该员工应该到哪条链表
		int empLinkNo = hashFun(emp.id);
		//将emp添加到对应的链表中
		empLinkArry[empLinkNo].add(emp);
	}
	
	//遍历所有的链表,遍历hashTab
	public void list() {
		for(int i = 0; i < size; i++) {
			empLinkArry[i].list(i);
		}
	}
	
	//编写一个散列函数,使用一个简单的取模法
	public int hashFun(int id){
		return id % size;
	}
	
	//根据输入的id,查找雇员
	public void findEmpId(int id){
		//使用散列函数确定到哪条链表查找
		int empLinkNO = hashFun(id);
		Emp emp = empLinkArry[empLinkNO].findEmpId(id);
		if(emp != null) {//找到
			System.out.printf("在第%d条链表中找到,雇员 id = %d\n", (empLinkNO + 1), id);
		}else{
			System.out.println("在哈希表中,没有找到该雇员~");
		}
	}
	
	//根据雇员的Id从哈希表中删除雇员
	public void delEmpId(int id){
		int index = hashFun(id);
		empLinkArry[index].delEmp(id);
	}
}

//表示一个雇员
class Emp{
	public int id;
	public String name;
	public Emp next; // next默认为 null
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

//创建一个EmpLink,表示链表
class EmpLink{
	//头指针,指向第一个Emp,因此这个链表的head是有效的,直接指向第一个Emp
	private Emp head;	//默认为null
	//添加雇员到链表
	//说明
	//1.假设,当添加雇员时,id是自增长,即id的分配总是从小到大
	//  因此可以将该雇员直接加入到本链表的最后即可
	public void add(Emp emp){
		//如果是添加第一个雇员
		if(head == null){
			head = emp;
			return;
		}
		//如果不是第一个,则使用一个辅助指针,帮助定位
		Emp curEmp = head;
		while(true){
			if(curEmp.next == null){	//到达链表的最后
				break;
			}
			curEmp = curEmp.next;	//后移
		}
		//退出时直接将emp,加入链表
		curEmp.next = emp;
	}
	//遍历链表
	public void list(int nums){
		if(head == null){	//链表为空
			System.out.println("第" + (nums+1) + "链表为空");
			return;
		}
		System.out.println("第" + (nums+1) + "链表的信息:");
		Emp curEmp = head;	//辅助指针
		while(true){
			System.out.printf("=> id = %d name = %s\t",curEmp.id,curEmp.name);
			if(curEmp.next == null){	//说明curEmp已经是最后的节点
				break;
			}
			curEmp = curEmp.next;	//后移
		}
		System.out.println();
	}
	
	//根据id查询雇员
	//如果查找到,就返回Emp,没有找到,就返回null
	public Emp findEmpId(int id){
		//判断链表是否为空
		if(head == null){
			System.out.println("链表为空.");
			return null;
		}
		//辅助指针
		Emp curEmp = head;
		while(true){
			if(curEmp.id == id){	//找到
				break;	//此时curEmp就指向要查找的雇员
			}
			//退出
			if(curEmp.next == null){	//未找到该雇员
				curEmp = null;
			}
			curEmp = curEmp.next;	//后移
		}
		return curEmp;
	}
	
	//删除雇员
	public void delEmp(int id){
		if(head == null){
			System.out.println("没有这个员工！！！");
			return;
		}
		//如果删除的是头节点
		if(head.id == id){
			head = head.next;
			return;
		}
		//如果删除的不是头节点
		Emp temp = head;
		while(temp.next != null){
			if (temp.next.id == id) {
                temp.next = temp.next.next;
                System.out.println("Id为" + id + "的员工已经被删除~~");
                return;
            }
		}
		System.out.println("没有这个员工！！！");
	}
}