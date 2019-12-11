<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
<div class="nav">
	<div class="mainnav">
		<ul class="uptitle">
			<li class="nav_on"><span class="nav_icon1"></span><a class="n_a"
				href="${basePath }sale/visitSaleGoodsMainPage.do">销售管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="商品编辑_销售商品管理.html" class="later">销售管理</a>
								<a href="商品编辑_销售商品管理.html">销售商品管理</a>|
								<a href="saleChannel/index.do">渠道销售商品管理</a>|
								<a href="商品编辑_商品发布.html">商品发布</a>|
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li><span class="nav_icon2"></span><a class="n_a" href="#1">渠道库存管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="#" class="later">渠道库存管理</a><a href="#">渠道库存查询</a>|
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li><span class="nav_icon3"></span><a class="n_a" href="#!">活动管理</a>
			</li>
			<li>
			  <span class="nav_icon4"></span>
			  <a class="n_a" href="order/index.do">订单管理</a>
			  <div class="subnav">
			    <ul>
				  <li><p>
				    <a href="order/index.do" class="later">订单管理</a>
				    <a href="#">当前订单</a>|
				    <a href="#">退单处理</a>|
				    <a href="#">历史订单</a>|
				    <a href="#">订单量统计</a>|
				    <a href="#">商品订单统计</a>|
				    <a href="#">销售量统计</a>|
				    <a href="#">差价订单统计</a>|
				  </p></li>
				</ul>
			  </div>
			</li>
			<li><span class="nav_icon5"></span><a class="n_a" href="#">号码管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="${basePath }number/find/showNumber.do" class="later">号码管理</a>
								<a href="${basePath }number/find/showNumber.do">号码信息查询</a>
								|<a href="${basePath }number/group/showNumGroup.do">号码分组管理</a>
								|<a href="#">号码调组</a>|<a href="#">号码下架</a>
								|<a href="${basePath }number/rule/showMobileRule.do">靓号规则管理</a>
								|<a href="${basePath }number/fav/showFavorable.do">普通预存款管理</a>
								|<a href="${basePath }number/grade/showMobileGrade.do">靓号预存款管理</a>|
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li><span class="nav_icon6"></span><a class="n_a"
				href="${basePath }mall/visitMall.do">商城管理</a>
				<div class="subnav">
					<ul>
						<li>
								<a href="${basePath }mall/visitMall.do" class="later">商城列表</a><a
									href="${basePath }mall/modifyMall.do?type=add">新增商城</a>
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li><span class="nav_icon7"></span><a class="n_a"
				href="${basePath }channel/toChannel.do">渠道管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="${basePath }channel/toChannel.do" class="later">渠道管理</a>
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li><span class="nav_icon10"></span><a class="n_a"
				href="${basePath }payMode/visitPayMode.do">支付管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="${basePath }payMode/visitPayMode.do" class="later">支付方式管理</a>
								<a href="${basePath }payMode/visitPayMode.do">支付方式管理</a>|<a
									href="${basePath }payProvider/visitPayProvider.do">支付供应商管理</a>|<a
									href="c支付管理_分期付款管理.html">分期付款管理</a>|<a href="${basePath }payTradeLog/visitPayTradeLog.do">支付日志管理</a>|
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li><span class="nav_icon8"></span><a class="n_a"
				href="b商品管理_商品管理_套餐类型.html">商品管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="b商品管理_商品管理_商品_上网卡.html" class="later">实物商品管理</a>
							</p>
							<p>
								<a href="b商品管理_商品管理_3G套餐.html" class="later">套餐管理</a><a
									href="b商品管理_商品管理_套餐类型.html">套餐类型</a>|<a
									href="b商品管理_商品管理_3G套餐.html">3G套餐</a>|
									<a href="${basePath }goods/visitProduct.do">货品管理</a>|
							</p>
						</li>
					</ul>
				</div>
			</li>
			<li>
			  <span class="nav_icon9"></span>
			  <a class="n_a" href="goodsBrand/index.do">基础资料管理</a>
		      <div class="subnav">
			  <ul>
				<li><p>
				  <a href="goodsBrand/index.do" class="later">基础资料管理</a>
				  <a href="goodsClass/index.do">商品品类管理</a>|
				  <a href="productClass/index.do">货品品类管理</a>|
				  <a href="attr/index.do">品类属性管理</a>|
				  <a href="goodsBrand/index.do">商品品牌管理</a>|
				  <a href="productBrand/index.do">货品品牌管理</a>|
				  <a href="model/index.do">型号管理</a>|
				  <a href="label/index.do">标签管理</a>|
				  <a href="f基础资料管理_品牌管理.html">仓位管理</a>|
				  <a href="f基础资料管理_品牌管理.html">商圈管理</a>|
				</p></li>
			  </ul>
		      </div>
			</li>
			<li><span class="nav_icon11"></span><a class="n_a"
				href="f基础资料管理_品类管理.html">权限管理</a>
				<div class="subnav">
					<ul>
						<li>
							<p>
								<a href="#" class="later">系统管理</a><a href="#">渠道工号管理</a>|<a
									href="#">菜单管理</a>|<a href="#">组织机构管理</a>|
							</p>
							<p>
								<a href="#" class="later">用户管理</a><a href="#">用户组管理</a>|<a
									href="#">用户管理</a>|<a href="#">用户角色管理</a>|
							</p>
						</li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
</div>
