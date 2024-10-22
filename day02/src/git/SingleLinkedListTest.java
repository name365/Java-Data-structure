package git;
/*
 * 此代码已优化
 */
public class SingleLinkedListTest {

	public static void main(String[] args) {
		//测试一下
		//1.创建节点
		HeroNode hero = new HeroNode(1,"宋江","及时雨");
		HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode hero3 = new HeroNode(3,"吴用","智多星");
		HeroNode hero4 = new HeroNode(4,"公孙胜","入云龙");
		HeroNode hero5 = new HeroNode(5,"关胜","大刀");
		
		//创建一个链表
		SingleLinkedList singk = new SingleLinkedList();
		
		//添加数据到链表
//		singk.add(hero);
//		singk.add(hero2);
//		singk.add(hero4);
//		singk.add(hero3);
//		singk.add(hero5); 
		
		//添加按照编号的顺序
		singk.addByOrder(hero);
		singk.addByOrder(hero4);
		singk.addByOrder(hero2);
		singk.addByOrder(hero5); 
		singk.addByOrder(hero3);
//		singk.addByOrder(hero3);
		
		//显示链表
		singk.list();
		
		//测试修改节点的代码
		HeroNode hero6 = new HeroNode(5,"渣渣辉","一刀999");
		singk.update(hero6);
		
		//显示链表
		System.out.println("修改后的链表情况:");
		singk.list();
		
		//删除一个节点
		singk.del(2);
		singk.del(1);
		singk.del(3);
		singk.del(4);
		singk.del(5);
		System.out.println("删除后的情况:");
		singk.list();	//显示链表
	}
	
	//获取单链表的节点的个数(如果是带头节点的链表,需求不统计头结点)
	/**
	  * 
	  * @Description 
	  * @author subei
	  * @date 2020年5月22日下午5:09:46
	  * @param head 链表的头节点
	  * @return 返回的是有效的节点的个数
	 */
	public static int getLength(HeroNode head){
		if(head.next == null){
			return 0;
		}
		int length = 0;
		//定义一个临时的变量,未统计头节点
		HeroNode str = head.next;
		while(str != null){
			length++;
			str = str.next;	//即遍历
		}
		return length;
	}

}

//定义一个SingleLinkedList类,来管理我们的英雄
class SingleLinkedList{
	//先初始化一个头节点,头节点不要动(防止后期找不到此链表),不存放具体数据
	private HeroNode head = new HeroNode(0, "", "");
	
	//返回头节点
	public HeroNode getHead() {
		return head;
	}

	//第一种方式:第一种方法在添加英雄时，直接添加到链表的尾部
	//添加节点到单向链表,思路，当不考虑编号的顺序时
	//1.找到当前链表的最后节点
	//2.将最后这个节点的next域指向这个新的节点
	public void add(HeroNode heroNode){
		//由于头节点head不能动,所以需要一个辅助变量 temp
		HeroNode temp = head;
		//遍历链表,找到最后
		while(true){
			//找到链表的最后
			if(temp.next == null){	//判定找到了的条件
				break;
			}
			//如果没有找到,将temp后移
			temp = temp.next;
		}
		//当退出循环时,temp就指向了链表的最后
		//将最后这个节点的next --指向--》 新的节点
		temp.next = heroNode;
	}
	
	//修改节点的信息,根据id编号来修改,即id编号不改
	//说明
	//1.根据newHeroNode 的 id 来修改即可
	public void update(HeroNode newHeroNode){
		//判断是否为空
		if(head.next == null){	//链表为空
			System.out.println("链表为空！！！");
			return;
		}
		//找到需要修改的节点,根据 id 编号
		//定义一个临时变量
		HeroNode temp = head.next;
		boolean flag = false;	//表示是否找到该节点
		while(true){
			if(temp == null){
				break;	//已经遍历完链表
			}
			if(temp.id == newHeroNode.id){
				//找到了
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag 判断是否找到要修改的节点
		if(flag){
			temp.name = newHeroNode.name;
			temp.nickName = newHeroNode.nickName;
		}else{
			System.out.printf("没有找到编号为 %d 的节点.\n",newHeroNode.id);
		}
	}
	
	//删除节点
	//思路
	//1.由于头节点head不能动,所以需要一个辅助变量 temp来找到待删除节点的前一个节点
	//2.说明我们在比较时，是temp.next.id 和  需要删除的节点的id比较
	public void del(int id){
		HeroNode temp = head;
		boolean flag = false;	//标志是否找到带删除的节点
		while(true){
			if(temp.next == null){	//已经到链表的最后
				break;
			}
			if(temp.next.id == id){	//找到了带删除的节点的前一个节点
				flag = true;
				break;
			}
			temp = temp.next;	//temp后移，遍历
		}
		//判断flag
		if(flag){	//说明找到了
			//可以删除
			temp.next = temp.next.next;
		}else{
			System.out.printf("要删除的节点 %d 不存在。\n",id);
		}
	}
	
	
	//显示链表[遍历]
	public void list(){
		//判断链表是否为空
		if(head.next == null){
			System.out.println("链表为空！！！");
			return;
		}
		//由于头节点head不能动,所以需要一个辅助变量 temp
		HeroNode temp = head;
		while(true){
			//判断是否到链表的最后
			if(temp == null){
				break;
			}
			//如果不为空，输出节点的信息
			System.out.println(temp);
			//注意！！！！将next后移(因为不向后移动,会造成死循环)
			temp = temp.next;
		}
	}
	
	//第二种方式在添加英雄时，根据排名将英雄插入到指定位置
	//(如果有这个排名，则添加失败，并给出提示)
	public void addByOrder(HeroNode heroNode){
		//由于头节点head不能动,所以需要一个辅助变量 temp,找到添加的位置
		//由于是单链表，而找到temp是位于 添加位置的前一个节点,否则插入不了
		HeroNode temp = head;
		boolean flag = false;	//标识添加的编号是否存在，默认为false
		while(true){
			if(temp.next == null){	//说明已经在链表的最后
				break;	
			}
			if(temp.next.id > heroNode.id){	//位置找到了，就在temp的后面插入
				break;
			}else if(temp.next.id == heroNode.id){	//说明希望添加的heroNode的编号已然存在
				flag = true;	//说明编号存在
				break;
			}
			temp = temp.next;	//后移,遍历当前链表
		}
		//判断flag的值
		if(flag){	//不能添加,说明编号存在
			System.out.printf("准备插入的英雄编号%d已经存在了。无法加入\n",heroNode.id);
		}else{
			//插入到链表中,temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

}

//定义一个HeroNode,每个HeroNode对象就是一个节点
class HeroNode{
	public int id;
	public String name;
	public String nickName;	//别名,昵称
	public HeroNode next;	//指向下一个节点
	
	//构造器
	public HeroNode(int id, String name, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
	}

	//为了显示方便，重写toString方法
	@Override
	public String toString() {
		return "HeroNode [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}
}