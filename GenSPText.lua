local outFile = nil;
local modName = "aknidl";
local blockName = "grid_normal";

local function GenModelBlockItem()
	local path = string.format("1.txt");
	outFile = io.open(path,"w");

	local tp = {'a','b','c','d','e','f','g','h','i','j','k','l'}

 	for i = 1, 12 do
		local cur_tp = tp[i];
		outFile:write('M_'..cur_tp..", ");

	end

	outFile:close();
end

GenModelBlockItem();

print("Done");
